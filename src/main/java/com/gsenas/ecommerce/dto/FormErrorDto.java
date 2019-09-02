package com.gsenas.ecommerce.dto;

public class FormErrorDto {

	private String field;
	private String description;
	
	public FormErrorDto(String field, String description) {
		super();
		this.field = field;
		this.description = description;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
