package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    Optional<Distrito> findByNombreDistrito(String nombreDistrito);
}

