package com.vicheak.phoneshop.project.dto.report;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {
	//productId, productName, totalUnit, totalAmount
	private Long productId; 
	private String productName; 
	private Integer totalUnit; 
	private BigDecimal totalAmount; 
}
