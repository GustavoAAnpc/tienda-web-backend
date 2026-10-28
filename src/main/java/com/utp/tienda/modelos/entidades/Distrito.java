package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "distrito")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distrito")
    private Integer idDistrito;

    @Column(name = "nombre_distrito", nullable = false, length = 80)
    private String nombreDistrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    public Distrito() {}

    public Integer getIdDistrito() { return idDistrito; }
    public void setIdDistrito(Integer idDistrito) { this.idDistrito = idDistrito; }

    public String getNombreDistrito() { return nombreDistrito; }
    public void setNombreDistrito(String nombreDistrito) { this.nombreDistrito = nombreDistrito; }

    public Provincia getProvincia() { return provincia; }
    public void setProvincia(Provincia provincia) { this.provincia = provincia; }
}

