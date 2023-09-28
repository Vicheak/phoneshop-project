package com.vicheak.phoneshop.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
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
	
	@Override
	public Brand getById(Integer id) {
		/*Optional<Brand> brandOptional = brandRepository.findById(id);
		if(brandOptional.isPresent()) {
			return brandOptional.get();
		}*/
		//throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = " + id + " not found");
		//throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id));
		
		/*return brandRepository.findById(id)
				.orElseThrow(() -> 
					new HttpClientErrorException(HttpStatus.NOT_FOUND, 
							String.format("Brand with id = %d not found", id)));*/
		
		/*return brandRepository.findById(id)
				.orElseThrow(() -> 
					new ApiException(HttpStatus.NOT_FOUND, 
							String.format("Brand with id = %d not found", id)));*/
		
		return brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id));
	}
	
	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName()); //@TODO improve update
		return brandRepository.save(brand);
	}
	
	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}
	
	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}
	
}
