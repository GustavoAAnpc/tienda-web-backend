package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findByNombreProvincia(String nombreProvincia);
}

