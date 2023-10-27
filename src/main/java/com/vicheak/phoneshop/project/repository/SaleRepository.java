package com.vicheak.phoneshop.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicheak.phoneshop.project.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
