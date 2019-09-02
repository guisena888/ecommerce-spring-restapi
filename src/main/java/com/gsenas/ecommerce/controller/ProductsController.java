package com.gsenas.ecommerce.controller;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gsenas.ecommerce.controller.form.ProductForm;
import com.gsenas.ecommerce.model.Product;
import com.gsenas.ecommerce.repository.ProductsRepository;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@GetMapping
	public List<Product> find(String productName) {
		List<Product> products = new ArrayList<Product>();
		if(productName == null) {
			products = productsRepository.findAll();
		}
		else {
			products = productsRepository.findByProductName(productName);
		}
		return products;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Product> create(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder) {
		Product product = productForm.convert();
		productsRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
		

	
}
