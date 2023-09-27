package com.vicheak.phoneshop.project.service;

import com.vicheak.phoneshop.project.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id); 
	Brand update(Integer id, Brand brandUpdate); 
}
