package com.vicheak.phoneshop.project.spec;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductImportHistoryFilter {
	private LocalDate startDate; 
	private LocalDate endDate; 
}
