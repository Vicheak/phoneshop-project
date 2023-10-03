package com.vicheak.phoneshop.project.service.utils;

import java.util.List;

public class GeneralUtils {
	
	//convert list of string to list of integer
	//["Dara", "Thida", "Seyha"]
	//=> [4, 5, 5]
	
	public static List<Integer> toIntegerList(List<String> list){
		return list.stream()
			.map(String::length)
			.toList();
	}
	
	public static List<Integer> getEvenNumber(List<Integer> list){
		return list.stream()
			.filter(x -> x%2 == 0)
			.toList();
	}

}
