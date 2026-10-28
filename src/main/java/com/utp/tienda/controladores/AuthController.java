package com.utp.tienda.controladores;

import com.utp.tienda.patrones.fachada.AuthFacade;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para el Módulo de Acceso (Login)
 * Arquitectura: MVC
 * Patrones aplicados: Facade, Proxy, Singleton
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO respuesta = authFacade.iniciarSesion(request.getNombreUsuario(), request.getContrasena());

        if (respuesta.isExitoso()) {
            return ResponseEntity.ok(respuesta);
        } else if (respuesta.isCuentaBloqueada()) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(respuesta); // HTTP 423 Locked
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta); // HTTP 401 Unauthorized
        }
    }
}

