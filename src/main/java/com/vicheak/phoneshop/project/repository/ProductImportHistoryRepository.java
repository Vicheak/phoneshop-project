package com.vicheak.phoneshop.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicheak.phoneshop.project.entity.ProductImportHistory;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> {

}
