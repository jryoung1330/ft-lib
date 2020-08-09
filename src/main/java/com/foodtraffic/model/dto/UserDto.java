package com.foodtraffic.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private List<VendorDto> favorites;
}