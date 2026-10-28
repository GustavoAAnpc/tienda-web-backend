package com.utp.tienda.modelos.servicios;

import com.utp.tienda.modelos.entidades.CorreoElectronico;
import com.utp.tienda.modelos.entidades.Usuario;
import com.utp.tienda.modelos.repositorios.CorreoElectronicoRepository;
import com.utp.tienda.modelos.repositorios.UsuarioRepository;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service("realAuthService")
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final CorreoElectronicoRepository correoRepository;
    private final PasswordEncryptionStrategy encryptionStrategy;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           CorreoElectronicoRepository correoRepository,
                           @Qualifier("sha256Strategy") PasswordEncryptionStrategy encryptionStrategy) {
        this.usuarioRepository = usuarioRepository;
        this.correoRepository = correoRepository;
        this.encryptionStrategy = encryptionStrategy;
    }

    @Override
    @Transactional
    public LoginResponseDTO procesarLogin(LoginRequestDTO request) {
        String acceso = request.getNombreUsuario().trim();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(acceso);

        // Si no se encuentra directamente por nombre de usuario, buscar por correo electrónico registrado
        if (usuarioOpt.isEmpty()) {
            Optional<CorreoElectronico> correoOpt = correoRepository.findByCorreoElectronico(acceso.toLowerCase());
            if (correoOpt.isPresent() && correoOpt.get().getDatosPersonales() != null) {
                usuarioOpt = Optional.ofNullable(correoOpt.get().getDatosPersonales().getUsuario());
            }
        }

        if (usuarioOpt.isEmpty()) {
            return LoginResponseDTO.fallo("Credenciales inválidas", 0, false);
        }

        Usuario usuario = usuarioOpt.get();

        // 1. Verificar si ya se encuentra bloqueado
        if (usuario.getBloqueado()) {
            return LoginResponseDTO.fallo("Cuenta bloqueada. Comuníquese con el administrador.", 0, true);
        }

        // 2. Verificar contraseña con la estrategia configurada
        boolean claveCorrecta = encryptionStrategy.verificar(request.getContrasena(), usuario.getContrasena());

        if (claveCorrecta) {
            // Éxito: Reiniciar contador de intentos y actualizar último acceso
            usuario.setIntentosFallidos(0);
            usuario.setUltimoAcceso(LocalDateTime.now());
            usuarioRepository.save(usuario);

            String token = "TK-" + UUID.randomUUID();
            String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombreRol() : "CLIENTE";
            return LoginResponseDTO.exito("Inicio de sesión exitoso", token, rolNombre, usuario.getNombreUsuario());
        } else {
            // Contraseña errónea: Incrementar contador
            int nuevosIntentos = usuario.getIntentosFallidos() + 1;
            usuario.setIntentosFallidos(nuevosIntentos);

            if (nuevosIntentos >= 3) {
                usuario.setBloqueado(true);
                usuarioRepository.save(usuario);
                return LoginResponseDTO.fallo("Cuenta bloqueada tras 3 intentos erróneos.", 0, true);
            } else {
                usuarioRepository.save(usuario);
                int restantes = 3 - nuevosIntentos;
                return LoginResponseDTO.fallo("Contraseña incorrecta. Le quedan " + restantes + " intento(s).", restantes, false);
            }
        }
    }
}
