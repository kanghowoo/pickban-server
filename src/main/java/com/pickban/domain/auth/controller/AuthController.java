package com.pickban.domain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickban.global.web.dto.ApiHeader;
import com.pickban.domain.auth.controller.dto.ReissueTokenRequest;
import com.pickban.domain.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> reissue(
            @Valid @RequestBody ReissueTokenRequest request) {
        String token = authService.reissueToken(request.getToken(), request.getRefreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add(ApiHeader.AUTH_TOKEN.getName(), token);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


}
