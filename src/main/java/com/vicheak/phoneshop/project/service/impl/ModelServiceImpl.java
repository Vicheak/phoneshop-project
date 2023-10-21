package com.vicheak.phoneshop.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.dto.ModelDTO;
import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.entity.Model;
import com.vicheak.phoneshop.project.mapper.ModelEntityMapper;
import com.vicheak.phoneshop.project.repository.ModelRepository;
import com.vicheak.phoneshop.project.service.BrandService;
import com.vicheak.phoneshop.project.service.ModelService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService{
	
	private final ModelRepository modelRepository;
	//private BrandService brandService; 

	@Override
	public Model save(Model model) {
		/*Integer brandId = model.getBrand().getId();
		brandService.getById(brandId);*/ 
		return modelRepository.save(model); 
	}

	@Override
	public List<Model> getByBrand(Integer brandId) {
		return modelRepository.findByBrandId(brandId);
	}

}
