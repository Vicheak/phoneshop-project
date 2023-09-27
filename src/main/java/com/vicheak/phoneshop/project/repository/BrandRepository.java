package com.vicheak.phoneshop.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vicheak.phoneshop.project.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
}