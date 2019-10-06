package com.gsenas.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gsenas.ecommerce.model.Product;

public interface ProductsRepository extends JpaRepository<Product,Long>{

	Page<Product> findByName(String name, Pageable pagination);

	

}
