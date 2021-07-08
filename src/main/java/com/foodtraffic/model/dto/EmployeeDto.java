package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {

	private long employeeId;

	private long vendorId;
	
    private boolean isAssociate;

    private boolean isAdmin;
}
