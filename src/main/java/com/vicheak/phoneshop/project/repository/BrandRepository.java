package com.vicheak.phoneshop.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vicheak.phoneshop.project.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{
	//using JPA query method (containing)
	List<Brand> findByNameLike(String name);
	List<Brand> findByNameContaining(String name);
}
