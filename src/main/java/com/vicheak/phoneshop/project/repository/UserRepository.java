package com.vicheak.phoneshop.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicheak.phoneshop.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
}
