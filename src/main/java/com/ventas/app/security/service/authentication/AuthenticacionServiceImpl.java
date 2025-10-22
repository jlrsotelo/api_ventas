package com.ventas.app.security.service.authentication;

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
import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticacionServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JWTService jWTService;

	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationServiceException {
		
		try {
			log.info("loginRequestDTO {}",loginRequestDTO);
			
			UserDetails userDetails= userDetailsService.loadUserByUsername(loginRequestDTO.userName());
			
			log.info("UserDetails {}",userDetails);
			
			if (isNull(userDetails)) {
				throw new AuthenticationServiceException("Invalid user or password.");
			}

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.userName(), loginRequestDTO.password()));
			
			LoginResponseDTO loginResponseDTO = jWTService.generateJwtToken(userDetails);
			
			return loginResponseDTO;
		} catch (Exception e) {
			throw e;
		}
		
	}

}
