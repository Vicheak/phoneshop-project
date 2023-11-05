package com.vicheak.phoneshop.project.service;

import com.vicheak.phoneshop.project.dto.SaleDTO;
import com.vicheak.phoneshop.project.entity.Sale;

public interface SaleService {
	void sell(SaleDTO saleDTO); 
	
	Sale getById(Long saleId); 
	
	void cancelSale(Long saleId); 
}
