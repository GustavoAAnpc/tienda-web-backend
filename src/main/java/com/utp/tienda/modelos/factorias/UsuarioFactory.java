package com.utp.tienda.modelos.factorias;

import com.utp.tienda.modelos.entidades.Rol;
import com.utp.tienda.modelos.entidades.Usuario;
import org.springframework.stereotype.Component;

/**
 * Patrón Creacional: Factory Method
 * Desacopla la lógica de instanciación y configuración inicial de los usuarios según su rol.
 */
@Component
public class UsuarioFactory {

    public Usuario crearUsuario(String username, String rawPasswordEncoded, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(username);
        usuario.setContrasena(rawPasswordEncoded);
        usuario.setRol(rol);
        usuario.setIntentosFallidos(0);
        usuario.setBloqueado(false);

        // Configuraciones específicas por tipo de perfil
        if (rol != null && "ADMINISTRADOR".equalsIgnoreCase(rol.getNombreRol())) {
            usuario.setTokenRecuperacion("TOKEN_ADMIN_SEED");
        }
        return usuario;
    }
}

