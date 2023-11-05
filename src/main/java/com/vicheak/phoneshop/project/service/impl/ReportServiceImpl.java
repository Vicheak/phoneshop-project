package com.vicheak.phoneshop.project.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.projection.ProductSold;
import com.vicheak.phoneshop.project.repository.SaleRepository;
import com.vicheak.phoneshop.project.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final SaleRepository saleRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate); 
	}
	
	
	
}
