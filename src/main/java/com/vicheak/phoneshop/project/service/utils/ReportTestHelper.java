package com.vicheak.phoneshop.project.service.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.ProductImportHistory;

public class ReportTestHelper {
	
	private static Product product1 = Product.builder()
			.id(1L)
			.name("Iphone 14 pro")
			.build();
	
	private static Product product2 = Product.builder()
			.id(2L)
			.name("Iphone 13 pro max")
			.build();
	
	private static Product product3 = Product.builder()
			.id(3L)
			.name("Samsung galaxy s10")
			.build();
	
	public static List<Product> getProducts(){
		return List.of(product1, product2);
	}

	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.dateImport(LocalDate.of(2023, 12, 23))
				.build(); 
		
		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(15)
				.pricePerUnit(BigDecimal.valueOf(1100))
				.dateImport(LocalDate.of(2023, 12, 24))
				.build(); 
		
		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(5)
				.pricePerUnit(BigDecimal.valueOf(1400))
				.dateImport(LocalDate.of(2023, 12, 25))
				.build(); 
		
		List<ProductImportHistory> productImportHistories = new ArrayList<>(); 
		productImportHistories.add(history1); 
		productImportHistories.add(history2); 
		productImportHistories.add(history3); 
		
		return productImportHistories;
	}
	
}
