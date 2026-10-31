package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.CorreoElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorreoElectronicoRepository extends JpaRepository<CorreoElectronico, Integer> {

    List<CorreoElectronico> findByDatosPersonales_IdDatosPersonales(Integer idDatosPersonales);

    @Query("SELECT c FROM CorreoElectronico c WHERE LOWER(c.correoElectronico) = LOWER(:correo)")
    Optional<CorreoElectronico> findByCorreoElectronico(@Param("correo") String correo);

    @Query("SELECT COUNT(c) > 0 FROM CorreoElectronico c WHERE LOWER(c.correoElectronico) = LOWER(:correo)")
    boolean existsByCorreoElectronico(@Param("correo") String correo);
}
