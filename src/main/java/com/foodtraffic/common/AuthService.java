package com.foodtraffic.common;

import com.foodtraffic.model.response.AuthUser;
import com.foodtraffic.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Slf4j
@Component
public class AuthService {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String AUTH_SERVER_URL;

    @Autowired
    RestTemplate restTemplate;

    public AuthUser getUserProfile(String accessToken) {
        AuthUser user = null;
        ResponseEntity<AuthUser> res =
                restTemplate.exchange(URI.create(AUTH_SERVER_URL + "userinfo"), HttpMethod.GET, getEntity(accessToken), AuthUser.class);

        if(!res.getStatusCode().is2xxSuccessful()) {
            log.error("Authorization Service call failed with error {}", res.getStatusCode());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            user = res.getBody();
        }

        return user;
    }

    private HttpEntity<String> getEntity(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(accessToken.substring(AppUtil.TOKEN_PREFIX));
        return new HttpEntity<>(headers);
    }
}
