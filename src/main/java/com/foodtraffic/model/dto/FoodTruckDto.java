package com.foodtraffic.model.dto;

import lombok.Data;

@Data
public class FoodTruckDto {

    private Long id;

    private String name;

    private Double longitude;

    private Double latitude;

    private String streetAddress;

    private String city;

    private String state;

    private String county;

    private int zipCode;

    private String locationDetails;

    private String description;

}
