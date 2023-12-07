package com.vicheak.phoneshop.project.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vicheak.phoneshop.project.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
	
	private final ProductImportHistoryFilter productImportHistoryFilter; 
	
	@Override
	public Predicate toPredicate(Root<ProductImportHistory> productImportHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(productImportHistoryFilter.getStartDate())) {
			predicates.add(cb.greaterThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getStartDate()));
		}
		
		if(Objects.nonNull(productImportHistoryFilter.getEndDate())) { 
			predicates.add(cb.lessThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getEndDate()));
		}
		
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new)); 

		return predicate; 
	}
	
}
