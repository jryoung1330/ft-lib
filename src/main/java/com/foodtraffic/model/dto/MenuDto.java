package com.foodtraffic.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class MenuDto {

	private Long id;

	private Long foodTruckId;

	private String description;

	private List<MenuItemDto> menuItems;
}
