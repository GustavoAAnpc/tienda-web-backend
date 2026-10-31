package com.utp.tienda.vistas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActualizarPerfilDTO(
        @NotBlank @Size(max = 80) String nombres,
        @NotBlank @Size(max = 80) String apellidos,
        @NotBlank @Size(max = 15) String documento,
        @Size(max = 20) String telefono,
        @Size(max = 150) String direccion,
        Integer idDistrito) {}
