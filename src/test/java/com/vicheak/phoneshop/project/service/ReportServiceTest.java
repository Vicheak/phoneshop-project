package com.vicheak.phoneshop.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vicheak.phoneshop.project.dto.report.ExpenseReportDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.ProductImportHistory;
import com.vicheak.phoneshop.project.repository.ProductImportHistoryRepository;
import com.vicheak.phoneshop.project.repository.ProductRepository;
import com.vicheak.phoneshop.project.repository.SaleDetailRepository;
import com.vicheak.phoneshop.project.repository.SaleRepository;
import com.vicheak.phoneshop.project.service.impl.ReportServiceImpl;
import com.vicheak.phoneshop.project.service.utils.ReportTestHelper;
import com.vicheak.phoneshop.project.spec.ProductImportHistorySpec;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	
	@Mock
	private SaleRepository saleRepository;
	@Mock
	private SaleDetailRepository saleDetailRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportHistoryRepository productImportHistoryRepository; 
	
	private ReportService reportService; 
	
	@BeforeEach
	public void setup() {
		reportService = new ReportServiceImpl(
				saleRepository, 
				saleDetailRepository, 
				productRepository, 
				productImportHistoryRepository); 
	}

	@Test
	public void testGetExpenseReport() {
		//given 
		List<ProductImportHistory> importHistories = 
				ReportTestHelper.getProductImportHistories();
		List<Product> products = ReportTestHelper.getProducts(); 
		
		//when
		//mock repositories
		when(productImportHistoryRepository
				.findAll(Mockito.any(ProductImportHistorySpec.class)))
		.thenReturn(importHistories);
		
		when(productRepository.findAllById(anySet()))
		.thenReturn(products); 
		
		List<ExpenseReportDTO> expenseReports =
				reportService.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		
		//then
		//expected results here
		assertEquals(2, expenseReports.size());
		ExpenseReportDTO expense1 = expenseReports.get(0); 
		assertEquals(1, expense1.getProductId());
		assertEquals("Iphone 14 pro", expense1.getProductName());
		assertEquals(10, expense1.getTotalUnit());
		assertEquals(12000, expense1.getTotalAmount().doubleValue());
	}
	
}
