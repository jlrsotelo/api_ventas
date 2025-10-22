package com.ventas.app.security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ventas.app.security.dto.LoginRequestDTO;
import com.ventas.app.security.dto.LoginResponseDTO;
import com.ventas.app.security.service.authentication.AuthenticationService;
import com.ventas.app.security.service.authentication.AuthenticationServiceException;

import java.util.HashMap;
import java.util.Map;

import static com.ventas.app.security.constants.SecurityConstant.*;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(API_AUTH)
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	
	@PostMapping(LOGIN_URL)
	public ResponseEntity<?> signinHeader(HttpServletResponse response, @RequestBody LoginRequestDTO request) {

		log.info("login ...");
		
		try {

			LoginResponseDTO  loginResponseDTO = authenticationService.login(request);
			log.info("token {}", loginResponseDTO.token());
			
			//log.info("refreshToken {}", loginResponseDTO.getRefreshToken());
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("token", TOKEN_BEARER_PREFIX + loginResponseDTO.token());
			//responseHeaders.set("RefreshToken", TOKEN_BEARER_PREFIX + loginResponseDTO.getRefreshToken());
			
			return ResponseEntity.ok().headers(responseHeaders).build();
			
		} catch (AuthenticationServiceException e) {
			e.printStackTrace();
			Map<String, String> body = new HashMap<>();
			body.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(body);
		}
	}
	
	@PostMapping(LOGIN_BODY_URL)
	public ResponseEntity<?> signinBody(HttpServletResponse response, @RequestBody LoginRequestDTO request) {

		log.info("login ...");
		
		try {

			LoginResponseDTO  loginResponseDTO = authenticationService.login(request);
			log.info("token {}", loginResponseDTO.token());
			
			//log.info("refreshToken {}", loginResponseDTO.getRefreshToken());
			
			//HttpHeaders responseHeaders = new HttpHeaders();
			//responseHeaders.set("Token", TOKEN_BEARER_PREFIX + loginResponseDTO.token());
			//responseHeaders.set("RefreshToken", TOKEN_BEARER_PREFIX + loginResponseDTO.getRefreshToken());
			Map<String, String> body = new HashMap<>();
			body.put("type", TOKEN_BEARER_PREFIX);
			body.put("token",loginResponseDTO.token());
			return ResponseEntity.ok().body(body);
			
		} catch (AuthenticationServiceException e) {
			e.printStackTrace();
			Map<String, String> body = new HashMap<>();
			body.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(body);
		}
	}
}
