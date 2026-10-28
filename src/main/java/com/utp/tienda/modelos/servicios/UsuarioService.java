package com.utp.tienda.modelos.servicios;

import com.utp.tienda.modelos.entidades.Rol;
import com.utp.tienda.modelos.entidades.Usuario;
import com.utp.tienda.modelos.factorias.UsuarioFactory;
import com.utp.tienda.modelos.repositorios.RolRepository;
import com.utp.tienda.modelos.repositorios.UsuarioRepository;
import com.utp.tienda.patrones.memento.UsuarioCaretaker;
import com.utp.tienda.patrones.memento.UsuarioMemento;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioFactory usuarioFactory;
    private final UsuarioCaretaker caretaker;
    private final PasswordEncryptionStrategy encryptionStrategy;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          UsuarioFactory usuarioFactory,
                          UsuarioCaretaker caretaker,
                          @Qualifier("sha256Strategy") PasswordEncryptionStrategy encryptionStrategy) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioFactory = usuarioFactory;
        this.caretaker = caretaker;
        this.encryptionStrategy = encryptionStrategy;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }

    @Transactional
    public Usuario registrarUsuario(String username, String rawPassword, String nombreRol) {
        if (usuarioRepository.existsByNombreUsuario(username)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        Rol rol = rolRepository.findByNombreRol(nombreRol)
                .orElseGet(() -> rolRepository.save(new Rol(null, nombreRol, "Rol creado dinámicamente")));

        String hash = encryptionStrategy.encriptar(rawPassword);
        // Patrón Factory Method
        Usuario nuevo = usuarioFactory.crearUsuario(username, hash, rol);
        return usuarioRepository.save(nuevo);
    }

    @Transactional
    public Usuario actualizarUsuario(Integer id, String nuevoUsername, Boolean bloqueado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));

        // Patrón Memento: Guardar estado anterior antes de modificar
        caretaker.guardarEstado(new UsuarioMemento(usuario.getNombreUsuario(), usuario.getIntentosFallidos(), usuario.getBloqueado()));

        if (nuevoUsername != null) usuario.setNombreUsuario(nuevoUsername);
        if (bloqueado != null) usuario.setBloqueado(bloqueado);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}

