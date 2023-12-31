package com.vicheak.phoneshop.project.config.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenVerifyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//check if the authorization header is valid 
		String authorizationHeader = request.getHeader("Authorization");
		if(Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		//stream for token
		String token = authorizationHeader.replace("Bearer ", "");
		String secretKey = "abcddsdfss123abcddsdfss123abcddsdfss123abcddsdfss123"; 
		
		Jws<Claims> claimsJws = Jwts.parser()
			.verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.build()
			.parseClaimsJws(token); 
		
		 Claims body = claimsJws.getBody(); 
		 String username = body.getSubject();
		 List<Map<String, String>> authorities = 
				 (List<Map<String, String>>) body.get("authorities");
		 
		 Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
		 	.map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
		 	.collect(Collectors.toSet()); 
		 
		 //use security context holder
		 Authentication authentication = 
				 new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		 
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		 filterChain.doFilter(request, response);
	}	

}
