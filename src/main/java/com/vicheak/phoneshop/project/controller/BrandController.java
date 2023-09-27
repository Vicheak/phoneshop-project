package com.vicheak.phoneshop.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.phoneshop.project.dto.BrandDTO;
import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.service.BrandService;
import com.vicheak.phoneshop.project.service.util.Mapper;

@RestController
@RequestMapping("brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;

	//create handler method
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));  
	}
	
}
