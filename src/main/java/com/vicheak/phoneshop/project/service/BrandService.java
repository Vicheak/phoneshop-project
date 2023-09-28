package com.vicheak.phoneshop.project.service;

import java.util.List;

import com.vicheak.phoneshop.project.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id); 
	Brand update(Integer id, Brand brandUpdate);
	List<Brand> getBrands(); 
	List<Brand> getBrands(String name); 
}
