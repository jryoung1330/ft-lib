package com.foodtraffic.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class MenuDto {

	private Long id;

	private Long vendorId;

	private String name;

	private String description;

	private Integer displayOrder;

	private List<MenuItemDto> menuItems;
}
