package com.vicheak.phoneshop.project.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vicheak.phoneshop.project.dto.ProductImportDTO;
import com.vicheak.phoneshop.project.entity.Product;
import com.vicheak.phoneshop.project.entity.ProductImportHistory;
import com.vicheak.phoneshop.project.exception.ApiException;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.mapper.ProductMapper;
import com.vicheak.phoneshop.project.repository.ProductImportHistoryRepository;
import com.vicheak.phoneshop.project.repository.ProductRepository;
import com.vicheak.phoneshop.project.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository ImportHistoryRepository;
	private final ProductMapper productMapper; 

	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),
				product.getColor().getName()); 
		product.setName(name); 
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Product", id)
				);
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		/*if(importDTO.getImportUnit() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, 
					"Import unit cannot be null"); 
		}*/
		
		//update available product unit
		Product product = getById(importDTO.getProductId()); 
		Integer availableUnit = 0; 
		
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product); 
		
		//save product import history
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
		ImportHistoryRepository.save(importHistory);
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product); 
	}

	/*@Override
	public void validateStock(Long productId, Integer numberOfUnit) {
		
	}*/
	
	@Override
	public Map<Integer, String> uploadProduct(MultipartFile file) {
		Map<Integer, String> map = new HashMap<>(); 
		
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("import"); 
			Iterator<Row> rowIterator = sheet.iterator();
			
			//@TODO improve checking error
			rowIterator.next(); //skip row header 
			
			while(rowIterator.hasNext()) {
				Integer rowNumber = 0; 
				try {
					
					Row row = rowIterator.next();
					int cellIndex = 0; 
					
					Cell cellNo = row.getCell(cellIndex++); 
					rowNumber = (int)cellNo.getNumericCellValue(); 
					
					Cell cellModelId = row.getCell(cellIndex++);
					Long modelId = (long)cellModelId.getNumericCellValue();
					
					Cell cellColorId = row.getCell(cellIndex++);
					Long colorId = (long)cellColorId.getNumericCellValue();
					
					Cell cellImportPrice = row.getCell(cellIndex++);
					Double importPrice = cellImportPrice.getNumericCellValue();
					
					Cell cellImportUnit = row.getCell(cellIndex++);
					Integer importUnit = (int)cellImportUnit.getNumericCellValue();
					if(importUnit < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, 
								"Unit must be greater than 0");
					}
					
					Cell cellImportDate = row.getCell(cellIndex++);
					LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
					
					Product product = getByModelIdAndColorId(modelId, colorId);
					
					//update available product unit
					Integer availableUnit = 0; 
					
					if(product.getAvailableUnit() != null) {
						availableUnit = product.getAvailableUnit();
					}
					
					product.setAvailableUnit(availableUnit + importUnit);
					productRepository.save(product); 
					
					//save product import history
					ProductImportHistory importHistory = new ProductImportHistory();
					importHistory.setDateImport(importDate);
					importHistory.setImportUnit(importUnit);
					importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
					importHistory.setProduct(product);
					
					ImportHistoryRepository.save(importHistory);
					
				} catch(Exception e) {
					map.put(rowNumber, e.getMessage()); 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Finish");
		
		return map; 
	}

	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		String text = "Product with model id = %d and color id = %d not found"; 
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(
						() -> new ApiException(HttpStatus.BAD_REQUEST, 
								text.formatted(modelId, colorId))
				); 
	}

}
