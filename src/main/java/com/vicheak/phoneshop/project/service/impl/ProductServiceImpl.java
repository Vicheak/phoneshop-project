package com.vicheak.phoneshop.project.service.impl;

import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.repository.ProductRepository;
import com.vicheak.phoneshop.project.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),
				product.getColor().getName()); 
		product.setName(name); 
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Product", id)
				);
	}

}
