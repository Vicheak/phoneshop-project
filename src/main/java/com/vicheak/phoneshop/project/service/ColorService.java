package com.vicheak.phoneshop.project.service;

import com.vicheak.phoneshop.project.entity.Color;

public interface ColorService {
	Color create(Color color); 
	Color getById(Long id); 
}
