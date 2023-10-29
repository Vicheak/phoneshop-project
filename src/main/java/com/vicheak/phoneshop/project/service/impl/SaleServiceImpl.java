package com.vicheak.phoneshop.project.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.dto.ProductSoldDTO;
import com.vicheak.phoneshop.project.dto.SaleDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.Sale;
import com.vicheak.phoneshop.project.entity.SaleDetail;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.repository.ProductRepository;
import com.vicheak.phoneshop.project.repository.SaleDetailRepository;
import com.vicheak.phoneshop.project.repository.SaleRepository;
import com.vicheak.phoneshop.project.service.ProductService;
import com.vicheak.phoneshop.project.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
	
	private final ProductService productService; 
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;
	
	@Override
	public void sell(SaleDTO saleDTO) {
		//validate product
		List<Long> productIds = validateProduct(saleDTO);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		//validate sale price and stock
		validateStock(saleDTO, productMap);
		
		//Save sale 
		saveSale(saleDTO, productMap);
	}
	
	private List<Long> validateProduct(SaleDTO saleDTO) {
		List<Long> productIds = saleDTO.getProducts().stream()
				.map(ProductSoldDTO::getProductId)
				.toList();
				
		//validate product
		productIds.forEach(productService::getById);
		return productIds; 
	}
	
	private void validateStock(SaleDTO saleDTO, Map<Long, Product> productMap) {
		//validate sale price and stock
		saleDTO.getProducts().
			forEach(ps -> {
				Product product = productMap.get(ps.getProductId());
				
				if(product.getSalePrice() == null) {
					throw new ApiException(HttpStatus.BAD_REQUEST,
							"Product [%s] is not yet set sale price"
							.formatted(product.getName())); 
				}
				
				Integer availableUnit = product.getAvailableUnit() != null ? product.getAvailableUnit() : 0; 
				if(availableUnit < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST,
							"Product [%s] is not enough in stock"
							.formatted(product.getName()));
				}
			});
	}
	
	private void saveSale(SaleDTO saleDTO, Map<Long, Product> productMap) {
		//Save sale
		Sale sale = new Sale(); 
		sale.setSoldDate(saleDTO.getSaleDate()); 
		saleRepository.save(sale);
		
		//Sale Detail
		saleDTO.getProducts()
			.forEach(ps -> {
				Product product = productMap.get(ps.getProductId());
				
				SaleDetail saleDetail = new SaleDetail(); 
				saleDetail.setAmount(product.getSalePrice());
				saleDetail.setProduct(product);
				saleDetail.setSale(sale);
				saleDetail.setUnit(ps.getNumberOfUnit());
				saleDetailRepository.save(saleDetail);
				
				//Cut stock
				Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
				product.setAvailableUnit(availableUnit); 
				productRepository.save(product); 
			});
	}
	
	/*private void validate1(SaleDTO saleDTO) {
		List<Long> productIds = saleDTO.getProducts().stream()
			.map(ProductSoldDTO::getProductId)
			.toList();
			
		//validate product
		productIds.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		//validate stock
		saleDTO.getProducts().
			forEach(ps -> {
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST,
							"Product [%s] is not enough in stock"
							.formatted(product.getName()));
				}
			});
	}*/

	/*private void validate(SaleDTO saleDTO) {
		 saleDTO.getProducts().forEach(ps -> {
    		 //validate product
			 Product product = productService.getById(ps.getProductId());
			 //validate stock
			 if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST,
							"Product [%s] is not enough in stock"
							.formatted(product.getName()));
			}
		 });
	}*/
	
	/*private void saveSale(SaleDTO saleDTO) {
		//Sale
		Sale sale = new Sale(); 
		sale.setSoldDate(saleDTO.getSaleDate()); 
		saleRepository.save(sale); 
		
		//Sale Detail
		saleDTO.getProducts().forEach(ps -> {
			//Product product = productService.getById(ps.getProductId());
			
			SaleDetail saleDetail = new SaleDetail(); 
			saleDetail.setAmount(null);
		});
	}*/

}
