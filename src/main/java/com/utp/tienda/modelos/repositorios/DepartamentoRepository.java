package com.utp.tienda.modelos.repositorios;

import com.utp.tienda.modelos.entidades.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByNombreDepartamento(String nombreDepartamento);
}

