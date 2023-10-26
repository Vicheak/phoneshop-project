package com.vicheak.phoneshop.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.phoneshop.project.dto.ProductDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.mapper.ProductMapper;
import com.vicheak.phoneshop.project.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService; 
	private final ProductMapper productMapper; 
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
		Product product = productMapper.toProduct(productDTO);
		product = productService.create(product); 
		return ResponseEntity.ok(product); 
	}

}
