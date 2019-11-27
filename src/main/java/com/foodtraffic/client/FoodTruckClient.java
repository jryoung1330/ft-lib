package com.foodtraffic.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "food-truck-service", url="http://localhost:8888/food-trucks")
public interface FoodTruckClient {

    @GetMapping("/check-food-truck")
    boolean checkFoodTruck(@RequestParam(name = "food-truck-name", required = false) String foodTruckName, @RequestParam(name = "id", required = false, defaultValue = "0") Long id);
}
