package com.foodtraffic.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OperationDto {

    private long id;

    private long vendorId;

    private List<OperationItemDto> operationItems;
}
