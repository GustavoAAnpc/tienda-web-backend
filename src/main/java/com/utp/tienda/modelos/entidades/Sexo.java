package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "sexo")
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Integer idSexo;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    public Sexo() {}

    public Sexo(Integer idSexo, String descripcion) {
        this.idSexo = idSexo;
        this.descripcion = descripcion;
    }

    public Integer getIdSexo() { return idSexo; }
    public void setIdSexo(Integer idSexo) { this.idSexo = idSexo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

