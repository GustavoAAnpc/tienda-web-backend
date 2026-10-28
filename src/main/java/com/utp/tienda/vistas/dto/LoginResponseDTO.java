package com.utp.tienda.vistas.dto;

public class LoginResponseDTO {

    private boolean exitoso;
    private String mensaje;
    private Integer intentosRestantes;
    private boolean cuentaBloqueada;
    private String token;
    private String rol;
    private String nombreUsuario;

    public LoginResponseDTO() {}

    public static LoginResponseDTO exito(String mensaje, String token, String rol, String nombreUsuario) {
        LoginResponseDTO r = new LoginResponseDTO();
        r.exitoso = true;
        r.mensaje = mensaje;
        r.token = token;
        r.rol = rol;
        r.nombreUsuario = nombreUsuario;
        r.cuentaBloqueada = false;
        r.intentosRestantes = 3;
        return r;
    }

    public static LoginResponseDTO fallo(String mensaje, int intentosRestantes, boolean cuentaBloqueada) {
        LoginResponseDTO r = new LoginResponseDTO();
        r.exitoso = false;
        r.mensaje = mensaje;
        r.intentosRestantes = Math.max(0, intentosRestantes);
        r.cuentaBloqueada = cuentaBloqueada;
        return r;
    }

    public boolean isExitoso() { return exitoso; }
    public String getMensaje() { return mensaje; }
    public Integer getIntentosRestantes() { return intentosRestantes; }
    public boolean isCuentaBloqueada() { return cuentaBloqueada; }
    public String getToken() { return token; }
    public String getRol() { return rol; }
    public String getNombreUsuario() { return nombreUsuario; }
}

