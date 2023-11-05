package com.vicheak.phoneshop.project.service;

import java.time.LocalDate;
import java.util.List;

import com.vicheak.phoneshop.project.projection.ProductSold;

public interface ReportService {
	
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	
}
