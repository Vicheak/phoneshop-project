package com.vicheak.phoneshop.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Brand> {

	Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId); 
	
}
