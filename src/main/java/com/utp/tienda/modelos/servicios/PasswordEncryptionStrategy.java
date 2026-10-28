package com.utp.tienda.modelos.servicios;

/**
 * Patrón de Comportamiento: Strategy
 * Define una familia de algoritmos de encriptación e intercambia su comportamiento en tiempo de ejecución.
 */
public interface PasswordEncryptionStrategy {
    String encriptar(String rawPassword);
    boolean verificar(String rawPassword, String encryptedPassword);
}

