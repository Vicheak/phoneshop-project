package com.vicheak.phoneshop.project.service.util;

import com.vicheak.phoneshop.project.dto.BrandDTO;
import com.vicheak.phoneshop.project.entity.Brand;

public class Mapper {
	
	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
		//brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand; 
	}
	
	public static BrandDTO toBrandDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO(); 
		brandDTO.setName(brand.getName());
		return brandDTO; 
	}

}
