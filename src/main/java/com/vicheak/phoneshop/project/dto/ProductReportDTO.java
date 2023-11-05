package com.vicheak.phoneshop.project.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductReportDTO {
	//productId, productName, unit, totalAmount
	private Long productId; 
	private String productName; 
	private Integer unit; 
	private BigDecimal totalAmount; 
}
