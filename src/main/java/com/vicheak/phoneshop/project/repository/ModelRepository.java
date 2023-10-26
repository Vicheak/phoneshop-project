package com.vicheak.phoneshop.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vicheak.phoneshop.project.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
	List<Model> findByBrandId(Long brandId); 
}
