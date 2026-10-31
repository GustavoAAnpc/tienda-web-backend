package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "telefono")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefono")
    private Integer idTelefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_datos_personales", nullable = false)
    private DatosPersonales datosPersonales;

    @Column(name = "numero_telefono", nullable = false, length = 20)
    private String numeroTelefono;

    @Column(name = "tipo_telefono", length = 30)
    private String tipoTelefono;

    public Telefono() {}

    public Integer getIdTelefono() { return idTelefono; }
    public void setIdTelefono(Integer idTelefono) { this.idTelefono = idTelefono; }

    public DatosPersonales getDatosPersonales() { return datosPersonales; }
    public void setDatosPersonales(DatosPersonales datosPersonales) { this.datosPersonales = datosPersonales; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public String getTipoTelefono() { return tipoTelefono; }
    public void setTipoTelefono(String tipoTelefono) { this.tipoTelefono = tipoTelefono; }
}

