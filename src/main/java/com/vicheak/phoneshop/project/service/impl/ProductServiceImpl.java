package com.vicheak.phoneshop.project.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.dto.ProductImportDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.ProductImportHistory;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.mapper.ProductMapper;
import com.vicheak.phoneshop.project.repository.ProductImportHistoryRepository;
import com.vicheak.phoneshop.project.repository.ProductRepository;
import com.vicheak.phoneshop.project.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository ImportHistoryRepository;
	private final ProductMapper productMapper; 

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

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		/*if(importDTO.getImportUnit() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, 
					"Import unit cannot be null"); 
		}*/
		
		//update available product unit
		Product product = getById(importDTO.getProductId()); 
		Integer availableUnit = 0; 
		
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product); 
		
		//save product import history
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
		ImportHistoryRepository.save(importHistory);
	}

}
