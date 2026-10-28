package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.CorreoElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorreoElectronicoRepository extends JpaRepository<CorreoElectronico, Integer> {
    List<CorreoElectronico> findByDatosPersonales_IdDatosPersonales(Integer idDatosPersonales);
    Optional<CorreoElectronico> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);
}
