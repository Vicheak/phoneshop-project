package com.vicheak.phoneshop.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ProductSoldDTO {
	@NotNull @Positive
	private Long productId;
	@NotNull @Positive
	private Integer numberOfUnit; 
}
