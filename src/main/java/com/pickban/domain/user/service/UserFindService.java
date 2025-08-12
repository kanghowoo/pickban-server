package com.pickban.domain.user.service;

import org.springframework.stereotype.Service;

import com.pickban.global.error.exception.EntityNotFoundException;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.user.domain.repository.UserRepository;

@Service
public class UserFindService {
    private final UserRepository userRepository;

    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }
}
