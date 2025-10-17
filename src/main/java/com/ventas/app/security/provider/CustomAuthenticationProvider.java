package com.ventas.app.security.provider;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ventas.app.security.exception.SecurityAuthenticationException;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	//private final ParammeterService parammeterService; 
	
	private final PasswordEncoder passwordEncoder;
	
	private final String MSG_CREDENTIALS_ERROR="Credenciales (user or password) de autentiación incorrectas";
	
	@Value("${custom.security.horario.termino}")
	private Integer horarioTermino;

	public CustomAuthenticationProvider(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// Custom Logic authentication

		try {
			
			String userName = authentication.getName();
			String password = authentication.getCredentials().toString();
			String principal = authentication.getPrincipal().toString();

			log.info("userName => {}", userName);
			log.info("password => {}", password);
			log.info("principal => {}", principal);

			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (isNull(userDetails)) {
				throw new BadCredentialsException(MSG_CREDENTIALS_ERROR);
			}
			
			log.info("password DB=> {}", userDetails.getPassword());
			
			if (userDetails.getUsername().equals(userName) && passwordEncoder.matches(password,userDetails.getPassword())) { // userDetails.getPassword().equals(password)
				
				// Verifico horario
				
				
				log.info("Hour => {}", LocalDateTime.now().getHour());
				log.info("horarioTermino => {}", horarioTermino);
				
				if (LocalDateTime.now().getHour()>=horarioTermino) {
					
					// 
					
					throw new SecurityAuthenticationException("Horario no válido");
				}
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userName, password,userDetails.getAuthorities());
				return upat;
			}
			throw new BadCredentialsException(MSG_CREDENTIALS_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException(MSG_CREDENTIALS_ERROR);
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		
	}

}
