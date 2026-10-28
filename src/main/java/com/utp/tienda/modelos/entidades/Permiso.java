package com.utp.tienda.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "permiso")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permiso_id")
    private Integer permisoId;

    @Column(name = "nombre_permiso", nullable = false, unique = true, length = 50)
    private String nombrePermiso;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    public Permiso() {}

    public Permiso(Integer permisoId, String nombrePermiso, String descripcion) {
        this.permisoId = permisoId;
        this.nombrePermiso = nombrePermiso;
        this.descripcion = descripcion;
    }

    public Integer getPermisoId() { return permisoId; }
    public void setPermisoId(Integer permisoId) { this.permisoId = permisoId; }

    public String getNombrePermiso() { return nombrePermiso; }
    public void setNombrePermiso(String nombrePermiso) { this.nombrePermiso = nombrePermiso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

