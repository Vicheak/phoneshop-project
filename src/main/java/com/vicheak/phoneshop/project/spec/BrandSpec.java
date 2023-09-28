package com.vicheak.phoneshop.project.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vicheak.phoneshop.project.entity.Brand;

import lombok.Data;

@Data
public class BrandSpec implements Specification<Brand>{
	//brandFilter is a mandatory dependency 
	private final BrandFilter brandFilter;
	
	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(brandFilter.getName() != null) {
			//filter by name 
			//Predicate name = brand.get("name").in(brandFilter.getName());
			//Predicate name = cb.like(brand.get("name"), "%" + brandFilter.getName() + "%");
			Predicate name = cb.like(cb.upper(brand.get("name")), "%" + brandFilter.getName().toUpperCase() + "%");
			predicates.add(name); 
		}
		
		if(brandFilter.getId() != null) {
			//filter by id
			Predicate id = brand.get("id").in(brandFilter.getId());
			predicates.add(id); 
		}
		//convert from list to array dynamic
		return cb.and(predicates.toArray(Predicate[]::new));
	}
	
}
