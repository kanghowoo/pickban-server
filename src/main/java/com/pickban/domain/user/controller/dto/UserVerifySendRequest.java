package com.pickban.domain.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserVerifySendRequest {

    @NotEmpty
    @JsonProperty("email")
    private final String email;

    @JsonCreator
    public UserVerifySendRequest(@JsonProperty("email") String email) {
        this.email = email;
    }
}
