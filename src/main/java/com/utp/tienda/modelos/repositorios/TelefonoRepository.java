package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {
    List<Telefono> findByDatosPersonales_IdDatosPersonales(Integer idDatosPersonales);
}

