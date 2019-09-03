package com.gsenas.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsenas.ecommerce.model.Product;

public interface ProductsRepository extends JpaRepository<Product,Long>{

	List<Product> findByName(String name);

	

}
