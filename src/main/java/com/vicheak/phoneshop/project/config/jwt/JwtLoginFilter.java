package com.vicheak.phoneshop.project.config.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		ObjectMapper mapper = new ObjectMapper();
		// deserialization process
		try {
			LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);

			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
					loginRequest.getPassword());

			Authentication authenticated = authenticationManager.authenticate(authentication);

			return authenticated;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// after a successfull authentication, this method will be executed
		String secretKey = "abcddsdfss123abcddsdfss123abcddsdfss123abcddsdfss123"; 
		
		String token = Jwts.builder()
				.subject(authResult.getName())
				.issuedAt(new Date())
				.claim("authorities", authResult.getAuthorities())
				.expiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
				//.expiration(java.sql.Date.valueOf(LocalDate.now()))
				.issuer("phoneshop.com")
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
		
		//set the response => Bearer ${token}
		response.setHeader("Authorization", "Bearer " + token);
	}

}
