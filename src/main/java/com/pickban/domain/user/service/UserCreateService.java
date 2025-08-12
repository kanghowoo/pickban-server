package com.pickban.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.UserAlreadyExistException;
import com.pickban.domain.user.controller.dto.CreateUserRequest;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.user.domain.repository.UserRepository;
import com.pickban.domain.verification.service.VerificationService;

@Service
public class UserCreateService {
    private final UserRepository userRepository;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreateService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             VerificationService verificationService
                             ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationService = verificationService;
    }

    @Transactional
    public void create(CreateUserRequest userRequest) {
        if (isExistEmail(userRequest.getEmail())) {
            throw new UserAlreadyExistException(ErrorCode.EMAIL_ALREADY_EXIST);
        }

        if (isExistNickname(userRequest.getNickname())) {
            throw new UserAlreadyExistException(ErrorCode.NICKNAME_ALREADY_EXIST);
        }

        User user = User.builder()
                        .nickname(userRequest.getNickname())
                        .email(userRequest.getEmail())
                        .password(userRequest.getRawPassword())
                        .build();

        user.encryptPassword(passwordEncoder);
        userRepository.save(user);
        verificationService.sendVerifyEmail(user.getEmail());

    }

    private boolean isExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isExistNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
