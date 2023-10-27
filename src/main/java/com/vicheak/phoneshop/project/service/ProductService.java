package com.vicheak.phoneshop.project.service;

import java.math.BigDecimal;

import com.vicheak.phoneshop.project.dto.ProductImportDTO;
import com.vicheak.phoneshop.project.entity.Product;

public interface ProductService {
	
	Product create(Product product);
	
	Product getById(Long id); 
	
	/**
	 * This method is to save to two tables (product and product import history)
	 * @param importDTO is the request from client
	 */
	void importProduct(ProductImportDTO importDTO); 
	
	void setSalePrice(Long productId, BigDecimal price);
	
	//void validateStock(Long productId, Integer numberOfUnit);  
	
}
