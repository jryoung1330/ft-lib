package com.foodtraffic.util;

import com.foodtraffic.client.UserClient;
import com.foodtraffic.model.dto.UserDto;
import com.foodtraffic.model.response.ErrorResponse;
import feign.FeignException;

public class AppUtil {

    public static UserDto getUser(UserClient userClient, String accessToken) {
        try {
            return userClient.checkAccessHeader(accessToken);
        } catch (FeignException e) {
            throw ErrorResponse.responseException(e.status(), e.getMessage());
        }
    }


}
