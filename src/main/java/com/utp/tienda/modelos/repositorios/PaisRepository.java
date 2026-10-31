package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Optional<Pais> findByNombrePais(String nombrePais);
}

