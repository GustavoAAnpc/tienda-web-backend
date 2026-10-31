package com.utp.tienda.modelos.servicios;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Estrategia Concreta 2: Hashing con BCrypt
 */
@Service("bcryptStrategy")
public class BCryptEncryptionStrategy implements PasswordEncryptionStrategy {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean verificar(String rawPassword, String encryptedPassword) {
        if (rawPassword == null || encryptedPassword == null) return false;
        return encoder.matches(rawPassword, encryptedPassword);
    }
}

