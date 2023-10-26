package com.vicheak.phoneshop.project.service;

import com.vicheak.phoneshop.project.entity.Product;

public interface ProductService {
	
	Product create(Product product);
	
	Product getById(Long id); 
}
