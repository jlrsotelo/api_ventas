package com.ventas.app.security.service.authentication;

import com.ventas.app.security.dto.LoginRequestDTO;
import com.ventas.app.security.dto.LoginResponseDTO;

public interface AuthenticationService {

	LoginResponseDTO login(LoginRequestDTO loginRequestDTO, Boolean swRefreshToken, String refreshToken) throws AuthenticationServiceException;
}
