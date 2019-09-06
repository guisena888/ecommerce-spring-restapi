package com.gsenas.ecommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gsenas.ecommerce.controller.form.ProductForm;
import com.gsenas.ecommerce.model.Product;
import com.gsenas.ecommerce.repository.BrandRepository;
import com.gsenas.ecommerce.repository.ProductsRepository;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping
	public Page<Product> findAll(@RequestParam(required=false) String productName,
								 @PageableDefault(sort="id", size=5, direction=Direction.ASC) Pageable pagination) {

		if(productName == null) {
			Page<Product> products = productsRepository.findAll(pagination);
			return products;
		}
		else {
			Page<Product> products = productsRepository.findByName(productName, pagination);
			return products;
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Optional<Product> optional = productsRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Product> create(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder) {
		Product product = productForm.convert(brandRepository);
		productsRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid ProductForm productForm){
		Optional<Product> optional = productsRepository.findById(id);
		if(optional.isPresent()) {
			Product product = productForm.update(optional.get(), brandRepository);			
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Product> optional = productsRepository.findById(id);
		if(optional.isPresent()) {
			productsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
		

	
}
