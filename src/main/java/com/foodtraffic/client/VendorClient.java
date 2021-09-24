package com.foodtraffic.client;

import com.foodtraffic.model.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "vendor-service", url="http://localhost:8888/vendors")
public interface VendorClient {

    @GetMapping("/{vendorId}/menus")
    List<MenuDto> getMenusForVendor(@PathVariable(name = "vendorId") Long vendorId);

    @GetMapping("/check-vendor")
    boolean checkVendor(@RequestParam(name = "user-name", required = false) String userName,
                        @RequestParam(name = "id", required = false,  defaultValue = "0") Long id);

    @GetMapping("/{vendorId}/employees/{userId}")
    boolean isUserAnAdmin(@PathVariable(name = "vendorId") Long vendorId,
                          @PathVariable(name = "userId") Long userId);

}
