package com.vicheak.phoneshop.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleDTO {
	@NotEmpty @Valid
	private List<ProductSoldDTO> products;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate saleDate; 
}
