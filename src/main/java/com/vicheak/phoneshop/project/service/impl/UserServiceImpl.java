package com.vicheak.phoneshop.project.service.impl;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.config.security.AuthUser;
import com.vicheak.phoneshop.project.config.security.RoleEnum;
import com.vicheak.phoneshop.project.config.security.UserService;
import com.vicheak.phoneshop.project.entity.User;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Primary 
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(
					() -> new ApiException(HttpStatus.NOT_FOUND, 
							"User has not been found!")
			); 
		
		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getRole().getAuthorities())
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		
		return Optional.ofNullable(authUser);
	}

}
