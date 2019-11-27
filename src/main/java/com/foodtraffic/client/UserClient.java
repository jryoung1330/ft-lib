package com.foodtraffic.client;

import com.foodtraffic.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-service", url="http://localhost:8889/users")
public interface UserClient {

    @GetMapping("/token")
    UserDto checkAccessHeader(@RequestHeader(value = "Cookie") String accessToken);
}
