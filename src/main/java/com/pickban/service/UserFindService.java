package com.pickban.service;

import org.springframework.stereotype.Service;

import com.pickban.common.error.exception.EntityNotFoundException;
import com.pickban.entity.User;
import com.pickban.repository.UserRepository;

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
