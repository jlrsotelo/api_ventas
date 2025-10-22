package com.ventas.app.security.token;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.ventas.app.security.dto.LoginResponseDTO;

import static com.ventas.app.security.constants.SecurityConstant.*;

@Slf4j
@Component
public class JWTServiceImpl implements JWTService {

	@Override
	public LoginResponseDTO generateJwtToken(UserDetails userDetails) {

		log.info("generateJwtToken...", userDetails);

		Collection<?> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		Map<String, Object> claims = new HashMap<>();

		claims.put(AUTHORITIES, authorities);
		// claims.put(SegurityConstant.USER_ID, customUserDetails.get);
		//claims.put(SegurityConstant.ORGANIZATION_ID, customUserDetails.getOrganization());
		//claims.put(SecurityConstant.USER_TYPE, customUserDetails.getUserType());

		String token = Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuer(ISSUER_INFO)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(getSigningSecretKey())
				.compact();
		/*
		String refreshToken = Jwts.builder().claims(claims).subject(customUserDetails.getUsername())
				.issuer(SegurityConstant.ISSUER_INFO).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + SegurityConstant.TOKEN_REFRESH_EXPIRATION_TIME))
				.signWith(getSigningSecretKey()).compact();
		*/
		return LoginResponseDTO.builder().token(token).build();//.refreshToken(refreshToken)
	}

	/*
	@Override
	public String generateJwtTokenFromRefreshToken(CustomUserDetails customUserDetails) {
		
		Collection<?> authorities = customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		Map<String, Object> claims = new HashMap<>();

		claims.put(SegurityConstant.AUTHORITIES, authorities);
		// claims.put(SegurityConstant.USER_ID, customUserDetails.get);
		claims.put(SegurityConstant.ORGANIZATION_ID, customUserDetails.getOrganization());
		claims.put(SegurityConstant.USER_TYPE, customUserDetails.getUserType());

		String token = Jwts.builder().claims(claims).subject(customUserDetails.getUsername())
				.issuer(SegurityConstant.ISSUER_INFO).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + SegurityConstant.TOKEN_EXPIRATION_TIME))
				.signWith(getSigningSecretKey()).compact();
		return token;
	}*/

	@Override
	public String getUserNameFromJwtToken(String token) {
		return getAllClaims(token).getSubject();
	}

	@Override
	public Claims getAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningSecretKey()).build().parseSignedClaims(token).getPayload();
	}

	@Override
	public boolean validateJwtToken(String token) {
		//log.info("token " + token);
		try {
			Jwts.parser().verifyWith(getSigningSecretKey()).build();
			return true;
		} catch (Exception e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		}
		return false;
	}

	@Override
	public String getJwtToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

	private SecretKey getSigningSecretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SUPER_SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}