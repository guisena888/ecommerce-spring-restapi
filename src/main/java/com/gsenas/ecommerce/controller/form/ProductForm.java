package com.gsenas.ecommerce.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gsenas.ecommerce.model.Product;

public class ProductForm {
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String brand;
	
	@NotNull @NotEmpty
	private String category;
	
	public Product convert() {
		return new Product(name,category, brand);		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
