package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class MenuItemDto {
	
	private Long id;
	
	private Long menuId;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Integer calories;
	
	private String remarks;
	
	private boolean isVegan;
	
	private boolean isVegetarian;
	
	private boolean isGlutenFree;
	
	private boolean isDairyFree;
	
	private boolean containsNuts;

}
