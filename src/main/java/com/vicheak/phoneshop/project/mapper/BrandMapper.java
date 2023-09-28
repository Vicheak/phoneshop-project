package com.vicheak.phoneshop.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.vicheak.phoneshop.project.dto.BrandDTO;
import com.vicheak.phoneshop.project.entity.Brand;

@Mapper
public interface BrandMapper {
	
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	//source = dto, target = entity
	Brand toBrand(BrandDTO dto);
	
	BrandDTO toBrandDTO(Brand entity);
	
}
