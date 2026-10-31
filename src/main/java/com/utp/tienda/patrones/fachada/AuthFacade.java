package com.utp.tienda.patrones.fachada;

import com.utp.tienda.modelos.entidades.Usuario;
import com.utp.tienda.modelos.servicios.IAuthService;
import com.utp.tienda.modelos.servicios.UsuarioService;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import com.utp.tienda.vistas.dto.RegistroRequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.utp.tienda.modelos.servicios.SesionService;

/**
 * Patrón Estructural: Facade (Fachada)
 * Proporciona una interfaz unificada y de alto nivel que oculta la complejidad
 * de los subsistemas de autenticación, validación de intentos y seguridad.
 */
@Component
public class AuthFacade {

    private final IAuthService authService;
    private final UsuarioService usuarioService;
    private final SesionService sesionService;

    public AuthFacade(@Qualifier("authProxy") IAuthService authService,
                      UsuarioService usuarioService, SesionService sesionService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
        this.sesionService = sesionService;
    }

    public LoginResponseDTO iniciarSesion(String nombreUsuario, String contrasena) {
        LoginRequestDTO dto = new LoginRequestDTO(nombreUsuario, contrasena);
        return authService.procesarLogin(dto);
    }

    public LoginResponseDTO registrarCliente(RegistroRequestDTO dto) {
        Usuario nuevo = usuarioService.registrarCliente(dto);
        String token = sesionService.crear(nuevo.getIdUsuario());
        String rolNombre = nuevo.getRol() != null ? nuevo.getRol().getNombreRol() : "CLIENTE";
        return LoginResponseDTO.exito("Registro completado con éxito", token, rolNombre, nuevo.getNombreUsuario());
    }
}
