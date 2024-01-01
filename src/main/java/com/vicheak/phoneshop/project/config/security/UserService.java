package com.vicheak.phoneshop.project.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	 Optional<AuthUser> findUserByUsername(String username);
	
}
