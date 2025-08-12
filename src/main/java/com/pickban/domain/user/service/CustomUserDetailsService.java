package com.pickban.domain.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.EntityNotFoundException;
import com.pickban.global.error.exception.UserNotVerifiedException;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.user.domain.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new UsernameNotFoundException(
                                          "User not found with email: " + email));

        if (!user.isVerified()) {
            throw new UserNotVerifiedException(ErrorCode.NOT_VERIFIED_USER);
        }

        return user;
    }

    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }
}
