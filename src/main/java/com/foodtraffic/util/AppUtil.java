package com.foodtraffic.util;

import com.foodtraffic.client.UserClient;
import com.foodtraffic.client.VendorClient;
import com.foodtraffic.model.dto.MenuDto;
import com.foodtraffic.model.dto.UserDto;
import com.foodtraffic.model.response.ErrorResponse;
import feign.FeignException;

import java.util.List;

public class AppUtil {

    public static UserDto getUser(UserClient userClient, String accessToken) {
        try {
            return userClient.checkAccessHeader(accessToken);
        } catch (FeignException e) {
            throw ErrorResponse.responseException(e.status(), e.getMessage());
        }
    }

    public static List<MenuDto> getMenu(VendorClient vendorClient, Long vendorId) {
        try {
            return vendorClient.getMenusForVendor(vendorId);
        } catch (FeignException e) {
            throw ErrorResponse.responseException(e.status(), e.getMessage());
        }
    }
}
