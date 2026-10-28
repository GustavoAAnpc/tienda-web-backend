package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Patrón de Diseño: Repository
 * Encapsula la lógica necesaria para acceder a las fuentes de datos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
}

