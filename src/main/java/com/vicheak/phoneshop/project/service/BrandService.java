package com.vicheak.phoneshop.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.vicheak.phoneshop.project.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long brandId); 
	Brand update(Long id, Brand brandUpdate);
	//List<Brand> getBrands(); 
	List<Brand> getBrands(String name); 
	//List<Brand> getBrands(Map<String, String> params);
	Page<Brand> getBrands(Map<String, String> params);
	//Brand deleteById(Integer id); 
}
