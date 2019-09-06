package com.gsenas.ecommerce.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gsenas.ecommerce.model.Brand;
import com.gsenas.ecommerce.model.Product;
import com.gsenas.ecommerce.repository.BrandRepository;

public class ProductForm {
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String brandName;
	
	private BigDecimal price;
	
	public Product convert(BrandRepository brandRepository) {
		Brand brand = brandRepository.findByName(brandName);
		Product product = new Product(name,description,brand,price);
		return product;
				
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product update(Product product, BrandRepository brandRepository) {
		Brand brand = brandRepository.findByName(brandName);
		product.setName(name);
		product.setDescription(description);
		product.setBrand(brand);
		product.setPrice(price);
		
		return product;
	}



	
	
}