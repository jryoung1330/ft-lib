package com.foodtraffic.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private long id;

    private long vendorId;

    private long userId;

    private double cost;

    private double tax;

    private double serviceFee;

    private double totalCost;

    private  String accountName;

    private LocalDateTime orderDate;

    private String status;

    private List<OrderItemDto> orderItems;
}
