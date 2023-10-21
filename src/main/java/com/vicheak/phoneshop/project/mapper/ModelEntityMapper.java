package com.vicheak.phoneshop.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.vicheak.phoneshop.project.dto.ModelDTO;
import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.entity.Model;
import com.vicheak.phoneshop.project.service.BrandService;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
	
	//factory method
	ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class); 
	
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId", source = "model.brand.id")
	ModelDTO toModelDTO(Model model);
	
	//provide own implementation
	/*default Brand toBrand(Integer brId) {
		Brand brand = new Brand();
		brand.setId(brId);
		return brand; 
	}*/
	
}
