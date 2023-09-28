package com.vicheak.phoneshop.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.repository.BrandRepository;
import com.vicheak.phoneshop.project.service.BrandService;
import com.vicheak.phoneshop.project.service.util.PageUtil;
import com.vicheak.phoneshop.project.spec.BrandFilter;
import com.vicheak.phoneshop.project.spec.BrandSpec;

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
	
	/*@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}*/
	
	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}
	
	/*@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			try {
				brandFilter.setId(Integer.parseInt(id));
			}catch(NumberFormatException e) {
				throw new ApiException(HttpStatus.NOT_ACCEPTABLE, 
						"Brand ID must be formatted as number!");
			}
		}
	
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		return brandRepository.findAll(brandSpec); 
	}*/
	
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			try {
				brandFilter.setId(Integer.parseInt(id));
			}catch(NumberFormatException e) {
				throw new ApiException(HttpStatus.NOT_ACCEPTABLE, 
						"Brand ID must be formatted as number!");
			}
		}
		
		// @TODO add to function for Pageable
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT; 
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
		
		//Pageable 
		//Page<Brand> page = brandRepository.findAll(brandSpec, Pageable.ofSize(1));
		
		Page<Brand> page = brandRepository.findAll(brandSpec, pageable); 
	
		return page;
	}
	
}
