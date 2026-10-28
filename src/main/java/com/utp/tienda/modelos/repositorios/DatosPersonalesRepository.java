package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Integer> {
    Optional<DatosPersonales> findByDni(String dni);
    Optional<DatosPersonales> findByUsuario_IdUsuario(Integer idUsuario);
}

