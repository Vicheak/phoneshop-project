package com.vicheak.phoneshop.project.service;

import java.time.LocalDate;
import java.util.List;

import com.vicheak.phoneshop.project.dto.ProductReportDTO;
import com.vicheak.phoneshop.project.dto.report.ExpenseReportDTO;
import com.vicheak.phoneshop.project.projection.ProductSold;

public interface ReportService {
	
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	
	List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
	
	List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate); 
	
}
