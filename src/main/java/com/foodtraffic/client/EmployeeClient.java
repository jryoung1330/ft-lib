package com.foodtraffic.client;

import com.foodtraffic.model.dto.EmployeeDto;
import com.foodtraffic.model.request.EmployeeRequest;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "employee-service", url="http://localhost:8890")
public interface EmployeeClient {
	
	@GetMapping("/food-trucks/{foodTruckId}/employees")
    public List<EmployeeDto> getEmployeesByFoodTruck(@PathVariable(name = "foodTruckId") Long foodTruckId,
    											     @RequestParam(name = "type", defaultValue = "all") String type);

    @GetMapping("/users/{userId}/employees")
    EmployeeDto getEmployeesByUser(@PathVariable(name = "userId") Long userId);

    @PostMapping("/food-trucks/{foodTruckId}/employees")
    EmployeeDto createEmployee(@PathVariable(name = "foodTruckId") Long foodTruckId, EmployeeRequest employee);
}
