package com.foodtraffic.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthUser {

    private String sub;
    private String nickname;
    private String name;
    private String picture;
    private String email;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
