package com.ventas.app.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ventas.app.security.token.JWTService;

import static java.util.Objects.isNull;
import static com.ventas.app.security.constants.SecurityConstant.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class SecurityTokenFilter extends OncePerRequestFilter {

	private final JWTService jWTService;
	
	private final  UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("SecurityTokenFilter...");

		// Validar Header y Prefijo
		final String authHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
		
		log.info("authHeader..."+authHeader);
		
		if (isNull(authHeader) || !authHeader.startsWith(TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(request, response);
			System.out.println("No existe Header o el prefijo no es valido");
			return;
		}
		
		if (authHeader.length()<9) {
			filterChain.doFilter(request, response);
			return;
		}
		final String jwt = authHeader.substring(7);// Token
		log.info("Token {}", jwt);
		final String user = jWTService.getUserNameFromJwtToken(jwt);
		log.info("User {}", user);

		if (!isNull(user) && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(user);
			
			log.info("userDetails {}", userDetails);
			
			if (jWTService.validateJwtToken(jwt)) {
				log.info("isTokenValid");
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(), userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(upat);
				SecurityContextHolder.setContext(context);
			} else {
				log.info("is not valid Token");
				return;
			}
		}
		
		log.info("end...");
		
		filterChain.doFilter(request, response);

	}

}
