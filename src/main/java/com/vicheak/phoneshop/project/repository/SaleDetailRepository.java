package com.vicheak.phoneshop.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicheak.phoneshop.project.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

	List<SaleDetail> findBySaleId(Long saleId);
	
}
