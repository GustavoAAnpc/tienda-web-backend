package com.utp.tienda.patrones.memento;

import java.time.LocalDateTime;

/**
 * Patrón de Comportamiento: Memento
 * Almacena el estado interno de un objeto Usuario sin violar el encapsulamiento.
 */
public class UsuarioMemento {
    private final String nombreUsuario;
    private final Integer intentosFallidos;
    private final Boolean bloqueado;
    private final LocalDateTime fechaInstantanea;

    public UsuarioMemento(String nombreUsuario, Integer intentosFallidos, Boolean bloqueado) {
        this.nombreUsuario = nombreUsuario;
        this.intentosFallidos = intentosFallidos;
        this.bloqueado = bloqueado;
        this.fechaInstantanea = LocalDateTime.now();
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public Integer getIntentosFallidos() { return intentosFallidos; }
    public Boolean getBloqueado() { return bloqueado; }
    public LocalDateTime getFechaInstantanea() { return fechaInstantanea; }
}

