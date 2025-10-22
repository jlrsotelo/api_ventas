package com.ventas.app.security.dto;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String token) {

}
