package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "correo_electronico")
public class CorreoElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correo")
    private Integer idCorreo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_datos_personales", nullable = false)
    private DatosPersonales datosPersonales;

    @Column(name = "correo_electronico", nullable = false, length = 100)
    private String correoElectronico;

    @Column(name = "tipo_correo", length = 30)
    private String tipoCorreo;

    public CorreoElectronico() {}

    public Integer getIdCorreo() { return idCorreo; }
    public void setIdCorreo(Integer idCorreo) { this.idCorreo = idCorreo; }

    public DatosPersonales getDatosPersonales() { return datosPersonales; }
    public void setDatosPersonales(DatosPersonales datosPersonales) { this.datosPersonales = datosPersonales; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTipoCorreo() { return tipoCorreo; }
    public void setTipoCorreo(String tipoCorreo) { this.tipoCorreo = tipoCorreo; }
}

