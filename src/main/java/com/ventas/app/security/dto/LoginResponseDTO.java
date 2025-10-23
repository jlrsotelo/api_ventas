package com.ventas.app.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginResponseDTO(String token,String refreshToken) {

}
