package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private long id;

    private long orderId;

    private long menuItemId;

    private long memo;
}
