package com.vicheak.phoneshop.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vicheak.phoneshop.project.config.security.RoleEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "permissions")
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name; 
	
}
