package com.ventas.app.security.service.authentication;

import static java.util.Objects.isNull;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ventas.app.security.dto.LoginRequestDTO;
import com.ventas.app.security.dto.LoginResponseDTO;
import com.ventas.app.security.token.JWTService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticacionServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JWTService jWTService;

	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, Boolean swRefreshToken, String refreshToken) throws AuthenticationServiceException {
		
		try {
			log.info("loginRequestDTO {}",loginRequestDTO);
			
			String userName;
			if(isNull(loginRequestDTO)) {
				userName = jWTService.getUserNameFromJwtToken(refreshToken);
			}else {
				userName = loginRequestDTO.userName();
			}
			
			UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
			
			log.info("UserDetails {}",userDetails);
			
			if (isNull(userDetails)) {
				throw new AuthenticationServiceException("Invalid user or password.");
			}
			
			if(!isNull(loginRequestDTO)) {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.userName(), loginRequestDTO.password()));
			}
			
			LoginResponseDTO loginResponseDTO = jWTService.generateJwtToken(userDetails, swRefreshToken, refreshToken);
			
			return loginResponseDTO;
		} catch (Exception e) {
			throw e;
		}
		
	}
}
