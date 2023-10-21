package com.vicheak.phoneshop.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.phoneshop.project.dto.ModelDTO;
import com.vicheak.phoneshop.project.entity.Model;
import com.vicheak.phoneshop.project.mapper.ModelMapper;
import com.vicheak.phoneshop.project.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models") //relative path
public class ModelController {
	
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO); 
		model = modelService.save(model); 
		return ResponseEntity.ok(modelMapper.toModelDTO(model)); 
	}
	
}
