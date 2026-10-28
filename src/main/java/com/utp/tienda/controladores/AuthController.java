package com.utp.tienda.controladores;

import com.utp.tienda.patrones.fachada.AuthFacade;
import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;
import com.utp.tienda.vistas.dto.RegistroRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador MVC para el Módulo de Acceso y Registro (Auth)
 * Arquitectura: MVC
 * Patrones aplicados: Facade, Proxy, Singleton, Factory Method, Strategy
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

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistroRequestDTO request) {
        try {
            LoginResponseDTO respuesta = authFacade.registrarCliente(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "exitoso", false,
                    "mensaje", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "exitoso", false,
                    "mensaje", "Error interno al procesar el registro: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAlias(@Valid @RequestBody RegistroRequestDTO request) {
        return registrar(request);
    }
}
