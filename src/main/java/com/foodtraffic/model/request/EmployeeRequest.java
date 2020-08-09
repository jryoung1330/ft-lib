package com.foodtraffic.model.request;

import lombok.Data;

@Data
public class EmployeeRequest {

    private Long id;

    private Long userId;

    private Long foodTruckId;

    private boolean isAssociate;

    private boolean isAdmin;

}