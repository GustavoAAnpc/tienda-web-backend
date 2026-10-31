package com.utp.tienda.modelos.servicios;

import com.utp.tienda.vistas.dto.LoginRequestDTO;
import com.utp.tienda.vistas.dto.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO procesarLogin(LoginRequestDTO request);
}

