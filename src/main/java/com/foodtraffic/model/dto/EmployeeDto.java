package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {

	private long userId;
	
	private long foodTruckId;
	
    private boolean isAssociate;

    private boolean isAdmin;

    private boolean isOwner;

    private String status;

}
