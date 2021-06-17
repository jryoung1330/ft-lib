package com.foodtraffic.client;

import com.foodtraffic.model.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "vendor-service", url="http://localhost:8888/vendors")
public interface VendorClient {

    @GetMapping("/{vendorId}/menus")
    List<MenuDto> getMenusForVendor(@PathVariable(name = "vendorId") Long vendorId);
}
