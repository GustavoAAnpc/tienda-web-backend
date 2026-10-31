package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_datos_personales", nullable = false)
    private DatosPersonales datosPersonales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_distrito", nullable = false)
    private Distrito distrito;

    @Column(name = "calle_avenida", nullable = false, length = 150)
    private String calleAvenida;

    @Column(name = "tipo_direccion", length = 40)
    private String tipoDireccion;

    @Column(name = "piso", length = 10)
    private String piso;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "referencia", length = 200)
    private String referencia;

    public Direccion() {}

    public Integer getIdDireccion() { return idDireccion; }
    public void setIdDireccion(Integer idDireccion) { this.idDireccion = idDireccion; }

    public DatosPersonales getDatosPersonales() { return datosPersonales; }
    public void setDatosPersonales(DatosPersonales datosPersonales) { this.datosPersonales = datosPersonales; }

    public Distrito getDistrito() { return distrito; }
    public void setDistrito(Distrito distrito) { this.distrito = distrito; }

    public String getCalleAvenida() { return calleAvenida; }
    public void setCalleAvenida(String calleAvenida) { this.calleAvenida = calleAvenida; }

    public String getTipoDireccion() { return tipoDireccion; }
    public void setTipoDireccion(String tipoDireccion) { this.tipoDireccion = tipoDireccion; }

    public String getPiso() { return piso; }
    public void setPiso(String piso) { this.piso = piso; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}

