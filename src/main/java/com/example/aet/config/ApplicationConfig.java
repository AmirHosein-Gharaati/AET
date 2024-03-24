package com.example.aet.config;

import com.example.aet.exception.UserNotFoundException;
import com.example.aet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.aet.exception.Messages.USER_NOT_FOUND;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("userDetails")
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }
}
