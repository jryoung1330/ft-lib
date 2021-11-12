package com.foodtraffic.util;

import com.foodtraffic.client.UserClient;
import com.foodtraffic.client.VendorClient;
import com.foodtraffic.model.dto.MenuDto;
import com.foodtraffic.model.dto.UserDto;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppUtilTest {

    @Test
    public void givenValidAccessToken_whenGetUser_thenReturnUser() {
        UserClient userClient = mock(UserClient.class);
        when(userClient.checkAccessHeader(any())).thenReturn(createMockUser());
        assertNotNull(AppUtil.getUser(userClient, "accessToken"));
    }

    @Test
    public void givenExceptionDuringCall_whenGetUser_thenThrowException() {
        UserClient userClient = mock(UserClient.class);
        Request request = Request.create(Request.HttpMethod.GET, "http://test.com", new HashMap<>(), "body".getBytes(), StandardCharsets.UTF_8, new RequestTemplate());
        when(userClient.checkAccessHeader(any()))
                .thenThrow(new FeignException
                        .BadRequest("BAD REQUEST", request, "This is a test".getBytes(StandardCharsets.UTF_8), null));
        assertThrows(ResponseStatusException.class, () -> AppUtil.getUser(userClient, "accessToken"));
    }


    @Test
    public void givenValidVendorId_whenGetMenu_thenReturnMenu() {
        VendorClient vendorClient = mock(VendorClient.class);
        when(vendorClient.getMenusForVendor(anyLong())).thenReturn(createMockMenuList());
        assertNotNull(AppUtil.getMenu(vendorClient, 100L));
    }

    @Test
    public void givenExceptionDuringCall_whenGetMenu_thenThrowException() {
        VendorClient vendorClient = mock(VendorClient.class);
        Request request = Request.create(Request.HttpMethod.GET, "http://test.com", new HashMap<>(), "body".getBytes(), StandardCharsets.UTF_8, new RequestTemplate());
        when(vendorClient.getMenusForVendor(anyLong()))
                .thenThrow(new FeignException
                        .BadRequest("BAD REQUEST", request, "This is a test".getBytes(StandardCharsets.UTF_8), null));
        assertThrows(ResponseStatusException.class, () -> AppUtil.getMenu(vendorClient, 100L));
    }

    @Test
    public void givenUpdatedObject_whenMergeObject_thenReturnMergedObject() {
        UserDto oldUser = createMockUser();
        UserDto newUser = new UserDto();
        newUser.setUsername("username");
        JpaRepository<UserDto, Long> repo = mock(JpaRepository.class);
        when(repo.findById(any())).thenReturn(Optional.of(oldUser));
        UserDto mergedUser = (UserDto) AppUtil.mergeObject(repo, newUser, 1L);
        assertEquals("username", mergedUser.getUsername());
    }

    @Test
    public void givenObjectNotFoundInRepo_whenMergeObject_thenThrowException() {
        UserDto oldUser = createMockUser();
        JpaRepository<UserDto, Long> repo = mock(JpaRepository.class);
        when(repo.findById(any())).thenReturn(Optional.empty());
        ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> AppUtil.mergeObject(repo, oldUser, 1L));
        assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
    }

    private UserDto createMockUser() {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setUsername("test");
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("test@example.com");
        return user;
    }

    private List<MenuDto> createMockMenuList() {
        List<MenuDto> menuList = new ArrayList<>();
        MenuDto menu = new MenuDto();
        menu.setId(1L);
        menu.setVendorId(100L);
        menu.setName("name");
        menu.setDescription("description");
        menu.setDisplayOrder(1);
        return menuList;
    }
}
