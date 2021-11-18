package com.foodtraffic.util;

import com.foodtraffic.client.UserClient;
import com.foodtraffic.client.VendorClient;
import com.foodtraffic.model.dto.MenuDto;
import com.foodtraffic.model.dto.UserDto;
import com.foodtraffic.model.response.Payload;
import feign.FeignException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class AppUtil {

    public static int TOKEN_PREFIX = 7;

    public static UserDto getUser(UserClient userClient, String accessToken) {
        try {
            return userClient.checkAccessHeader(accessToken);
        } catch (FeignException e) {
            throw ErrorUtil.responseException(e.status(), e.getMessage());
        }
    }

    public static List<MenuDto> getMenu(VendorClient vendorClient, Long vendorId) {
        try {
            return vendorClient.getMenusForVendor(vendorId);
        } catch (FeignException e) {
            throw ErrorUtil.responseException(e.status(), e.getMessage());
        }
    }

    public static Object mergeObject(JpaRepository<?, Long> repo, Object updatedObject, Long id) {
        Object mergedObject = null;
        Optional<?> optionalObject = repo.findById(id);

        if(optionalObject.isPresent()) {
            mergedObject = optionalObject.get();

            try {
                for (Field f : updatedObject.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if(f.get(updatedObject) != null) {
                        f.set(mergedObject, f.get(updatedObject));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return mergedObject;
    }

    public static Object mergeObject(Object updatedObject, Object mergedObject) {
        try {
            for (Field f : updatedObject.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(updatedObject) != null) {
                    f.set(mergedObject, f.get(updatedObject));
                }
            }
        } catch (IllegalAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return mergedObject;
    }

}
