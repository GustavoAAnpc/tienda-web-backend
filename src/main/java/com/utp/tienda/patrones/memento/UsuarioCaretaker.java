package com.utp.tienda.patrones.memento;

import org.springframework.stereotype.Component;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Patrón Memento: Caretaker (Conserje)
 * Custodia los estados previos de los usuarios para auditoría o reversión.
 */
@Component
public class UsuarioCaretaker {
    private final Deque<UsuarioMemento> historial = new ArrayDeque<>();

    public void guardarEstado(UsuarioMemento memento) {
        historial.push(memento);
    }

    public UsuarioMemento deshacer() {
        if (!historial.isEmpty()) {
            return historial.pop();
        }
        return null;
    }

    public int totalEstadosGuardados() {
        return historial.size();
    }
}

