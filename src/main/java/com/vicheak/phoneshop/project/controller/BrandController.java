package com.vicheak.phoneshop.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.phoneshop.project.dto.BrandDTO;
import com.vicheak.phoneshop.project.dto.ModelDTO;
import com.vicheak.phoneshop.project.dto.PageDTO;
import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.entity.Model;
import com.vicheak.phoneshop.project.mapper.BrandMapper;
import com.vicheak.phoneshop.project.mapper.ModelEntityMapper;
import com.vicheak.phoneshop.project.service.BrandService;
import com.vicheak.phoneshop.project.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService; 
	private final ModelEntityMapper modelMapper; 

	//create handler method
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		//Brand brand = Mapper.toBrand(brandDTO);
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));  
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand)); 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") Long brandId,
										 @RequestBody BrandDTO brandDTO){
		//Brand brand = Mapper.toBrand(brandDTO);
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));  
	}
	
	/*@GetMapping
	public ResponseEntity<?> getBrands(){
		List<BrandDTO> list = brandService.getBrands()
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());
		return ResponseEntity.ok(list); 
	}*/
	
	//@GetMapping("filter")
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> page = brandService.getBrands(params);
		
		PageDTO pageDTO = new PageDTO(page);
		
		/*List<BrandDTO> list = brandService.getBrands(params)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());*/
		return ResponseEntity.ok(pageDTO);
	}
	
	/*@DeleteMapping("{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") Integer id){
		Brand brand = brandService.deleteById(id); 
		return ResponseEntity.ok()
				.body(BrandMapper.INSTANCE.toBrandDTO(brand)); 
	}*/
	
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Long brandId){
		List<Model> models = modelService.getByBrand(brandId);
		List<ModelDTO> list = models.stream()
			.map(modelMapper::toModelDTO)
			.toList(); 
		return ResponseEntity.ok(list);   
	}
	
}
