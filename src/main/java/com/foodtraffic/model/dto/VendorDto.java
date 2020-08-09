package com.foodtraffic.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class VendorDto {

    private long id;

    private String userName;
    
    private String displayName;

    private double longitude;

    private double latitude;

    private String streetAddress;

    private String city;

    private String state;

    private String county;

    private int zipCode;

    private String locationDetails;

    private String description;
    
    private String status;
    
    private List<TagDto> tags;
    
}