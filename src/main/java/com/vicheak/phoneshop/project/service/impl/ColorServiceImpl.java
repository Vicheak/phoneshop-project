package com.vicheak.phoneshop.project.service.impl;

import org.springframework.stereotype.Service;

import com.vicheak.phoneshop.project.entity.Color;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.repository.ColorRepository;
import com.vicheak.phoneshop.project.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
	
	private final ColorRepository colorRepository;

	@Override
	public Color create(Color color) {
		return colorRepository.save(color); 
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Color ", id)
				);
	}
	
	

}
