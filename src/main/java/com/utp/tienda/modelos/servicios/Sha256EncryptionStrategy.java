package com.utp.tienda.modelos.servicios;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Estrategia Concreta 1: Hashing con SHA-256
 */
@Service("sha256Strategy")
@Primary
public class Sha256EncryptionStrategy implements PasswordEncryptionStrategy {

    @Override
    public String encriptar(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular hash SHA-256", e);
        }
    }

    @Override
    public boolean verificar(String rawPassword, String encryptedPassword) {
        if (rawPassword == null || encryptedPassword == null) return false;
        return encriptar(rawPassword).equalsIgnoreCase(encryptedPassword);
    }
}

