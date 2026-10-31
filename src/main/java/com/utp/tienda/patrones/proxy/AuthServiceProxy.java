package com.utp.tienda.patrones.proxy;

import com.utp.tienda.modelos.servicios.IAuthService;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Patrón Estructural: Proxy
 * Actúa como intermediario controlando y auditando el acceso al servicio real de autenticación.
 */
@Service("authProxy")
@Primary
public class AuthServiceProxy implements IAuthService {

    private final IAuthService realService;

    public AuthServiceProxy(@Qualifier("realAuthService") IAuthService realService) {
        this.realService = realService;
    }

    @Override
    public LoginResponseDTO procesarLogin(LoginRequestDTO request) {
        System.out.println("[AUTH_PROXY - AUDITORÍA] Petición de login para usuario: " + request.getNombreUsuario());

        // Control previo de validación superficial
        if (request.getNombreUsuario() == null || request.getNombreUsuario().trim().isEmpty()) {
            return LoginResponseDTO.fallo("Nombre de usuario vacío", 0, false);
        }

        // Delegación al servicio real
        LoginResponseDTO respuesta = realService.procesarLogin(request);

        // Post-procesamiento y log de seguridad
        if (respuesta.isExitoso()) {
            System.out.println("[AUTH_PROXY - ÉXITO] Acceso concedido a: " + request.getNombreUsuario());
        } else if (respuesta.isCuentaBloqueada()) {
            System.err.println("[AUTH_PROXY - ALERTA DE SEGURIDAD] La cuenta '" + request.getNombreUsuario() + "' se encuentra BLOQUEADA.");
        } else {
            System.out.println("[AUTH_PROXY - FALLO] Intento fallido para: " + request.getNombreUsuario());
        }

        return respuesta;
    }
}

