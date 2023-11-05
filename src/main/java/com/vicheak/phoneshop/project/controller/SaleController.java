package com.vicheak.phoneshop.project.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.phoneshop.project.dto.SaleDTO;
import com.vicheak.phoneshop.project.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {
	
	private final SaleService saleService; 
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid SaleDTO saleDTO){
		saleService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		saleService.cancelSale(saleId); 
		return ResponseEntity.ok().build();
	}
	
}
