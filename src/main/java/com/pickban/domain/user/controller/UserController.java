package com.pickban.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickban.global.error.exception.MailSendException;
import com.pickban.domain.user.controller.dto.CreateUserRequest;
import com.pickban.domain.user.controller.dto.UserApiShowResponse;
import com.pickban.domain.user.controller.dto.UserPasswordResetMailSendRequest;
import com.pickban.domain.user.controller.dto.UserVerifyRequest;
import com.pickban.domain.user.controller.dto.UserVerifyResponse;
import com.pickban.domain.user.controller.dto.UserVerifySendRequest;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.user.service.UserMapper;
import com.pickban.domain.user.service.UserCreateService;
import com.pickban.domain.verification.service.VerificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCreateService userCreateService;
    private final VerificationService verificationService;
    private final UserMapper userMapper;

    public UserController(UserCreateService userCreateService,
                          VerificationService verificationService,
                          UserMapper userMapper) {
        this.userCreateService = userCreateService;
        this.verificationService = verificationService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {
        try {
            userCreateService.create(request);
        } catch (MailSendException e) {
            return new ResponseEntity<>(e.getErrorCode().getMessage(), e.getErrorCode().getHttpStatus());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> show(@AuthenticationPrincipal User user) {
        System.out.println(user.getCreatedAt());
        UserApiShowResponse response = userMapper.userToApiShowResponse(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@Valid @RequestBody UserVerifyRequest request) {
        String email = verificationService.verify(request.getToken());
        UserVerifyResponse response = UserVerifyResponse.builder()
                .email(email)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verify/send")
    public ResponseEntity<?> sendVerifyMail(@Valid @RequestBody UserVerifySendRequest request) {
        try {
            verificationService.sendVerifyEmail(request.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }

    }

    @PostMapping("/password/reset/send")
    public ResponseEntity<?> sendPasswordResetMail(
            @Valid @RequestBody UserPasswordResetMailSendRequest request) {
        verificationService.sendPasswordResetEmail(request.getEmail());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
