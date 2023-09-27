package com.vicheak.phoneshop.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.repository.BrandRepository;
import com.vicheak.phoneshop.project.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}
	
}
