package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {

	private long userId;
	
	private long vendorId;
	
    private boolean isAssociate;

    private boolean isAdmin;
}
