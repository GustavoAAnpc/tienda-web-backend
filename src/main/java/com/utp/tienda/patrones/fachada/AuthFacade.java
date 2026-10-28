package com.utp.tienda.patrones.fachada;

import com.utp.tienda.modelos.servicios.IAuthService;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Patrón Estructural: Facade (Fachada)
 * Proporciona una interfaz unificada y de alto nivel que oculta la complejidad
 * de los subsistemas de autenticación, validación de intentos y seguridad.
 */
@Component
public class AuthFacade {

    private final IAuthService authService;

    public AuthFacade(@Qualifier("authProxy") IAuthService authService) {
        this.authService = authService;
    }

    public LoginResponseDTO iniciarSesion(String nombreUsuario, String contrasena) {
        LoginRequestDTO dto = new LoginRequestDTO(nombreUsuario, contrasena);
        return authService.procesarLogin(dto);
    }
}

