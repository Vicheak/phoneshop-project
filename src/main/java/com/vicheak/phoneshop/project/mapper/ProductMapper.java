package com.vicheak.phoneshop.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vicheak.phoneshop.project.dto.ProductDTO;
import com.vicheak.phoneshop.project.dto.ProductImportDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.ProductImportHistory;
import com.vicheak.phoneshop.project.service.ColorService;
import com.vicheak.phoneshop.project.service.ModelService;
import com.vicheak.phoneshop.project.service.ProductService;

@Mapper(componentModel = "spring", 
	uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
	
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO); 
	
	@Mapping(target = "dateImport", source = "importDTO.importDate")
	@Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id", ignore = true)
	ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product);
	
}
