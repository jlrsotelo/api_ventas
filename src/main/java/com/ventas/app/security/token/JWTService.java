package com.ventas.app.security.token;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import com.ventas.app.security.dto.LoginResponseDTO;

public interface JWTService {

	LoginResponseDTO generateJwtToken(UserDetails userDetails);
	
	//String generateJwtTokenFromRefreshToken(UserDetails userDetails);
	
	String  getUserNameFromJwtToken(String token);

	Claims getAllClaims(String token);

	boolean validateJwtToken(String token); // owner, expiration

	String getJwtToken(HttpServletRequest request);

}