package com.pickban.domain.user.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserApiShowResponse {
    private long id;
    private String nickname;
    private String email;
//    private Role role;
//    private VerifyStatus verified;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
