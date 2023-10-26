package com.vicheak.phoneshop.project.service;

import java.util.List;
import com.vicheak.phoneshop.project.entity.Model;

public interface ModelService {
	Model save(Model model); 
	List<Model> getByBrand(Long brandId); 
	Model getById(Long id); 
}
