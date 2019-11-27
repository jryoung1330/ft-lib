package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class BillingAccountDto {

    private long id;

    private long foodTruckId;

    private String lastFourDigits;

    private int expiryYear;

    private int expiryMonth;

    private String nameOnCard;

    private int cardType;

}
