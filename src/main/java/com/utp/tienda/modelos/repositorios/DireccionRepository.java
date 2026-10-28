package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    List<Direccion> findByDatosPersonales_IdDatosPersonales(Integer idDatosPersonales);
}

