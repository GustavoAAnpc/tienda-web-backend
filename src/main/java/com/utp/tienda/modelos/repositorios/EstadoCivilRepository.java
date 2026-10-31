package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
    Optional<EstadoCivil> findByDescripcion(String descripcion);
}

