package com.vicheak.phoneshop.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{
	List<Model> findByBrandId(Integer brandId); 
}
