package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Integer> {
    Optional<Sexo> findByDescripcion(String descripcion);
}

