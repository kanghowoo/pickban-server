package com.pickban.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPasswordResetMailSendRequest {
    @NotEmpty
    @Email
    @JsonProperty("email")
    private final String email;

    @JsonCreator
    public UserPasswordResetMailSendRequest(@JsonProperty("email") String email) {
        this.email = email;
    }
}
