package com.vicheak.phoneshop.project.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vicheak.phoneshop.project.entity.Sale;
import com.vicheak.phoneshop.project.entity.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
	
	private final SaleDetailFilter saleDetailFilter; 
	
	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		
		//join tables
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		
		if(Objects.nonNull(saleDetailFilter.getStartDate())) { //saleDetailFilter.getStartDate() != null
			predicates.add(cb.greaterThanOrEqualTo(saleDetail.get("soldDate"), saleDetailFilter.getStartDate()));
		}
		
		if(Objects.nonNull(saleDetailFilter.getEndDate())) { 
			predicates.add(cb.lessThanOrEqualTo(saleDetail.get("soldDate"), saleDetailFilter.getEndDate()));
		}
		
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new)); 

		return predicate; 
	}
	
}
