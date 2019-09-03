package com.gsenas.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsenas.ecommerce.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long>{
	
	Brand findByName(String name);

}
