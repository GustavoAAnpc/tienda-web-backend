package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_civil")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_civil")
    private Integer idEstadoCivil;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    public EstadoCivil() {}

    public EstadoCivil(Integer idEstadoCivil, String descripcion) {
        this.idEstadoCivil = idEstadoCivil;
        this.descripcion = descripcion;
    }

    public Integer getIdEstadoCivil() { return idEstadoCivil; }
    public void setIdEstadoCivil(Integer idEstadoCivil) { this.idEstadoCivil = idEstadoCivil; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

