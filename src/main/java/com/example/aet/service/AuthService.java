package com.example.aet.service;

import com.example.aet.model.user.User;
import com.example.aet.model.user.dto.AuthenticationDetail;
import com.example.aet.model.user.dto.LoginRequest;
import com.example.aet.model.user.dto.SignUpRequest;
import com.example.aet.repository.UserRepository;
import com.example.aet.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.aet.exception.Messages.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationDetail login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD));

        if (!isAuthenticated(user, request.password())) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
        }

        String token = jwtService.generateToken(user);

        return new AuthenticationDetail(token);
    }

    public AuthenticationDetail signup(SignUpRequest request) {
        validate(request);

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.username(), encodedPassword);
        userRepository.insert(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationDetail(token);
    }

    private void validate(SignUpRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            log.error("password and confirm password are not equal");
            throw new BadCredentialsException(PASSWORD_AND_CONFIRM_PASSWORD_NOT_EQUAL);
        }

        boolean userNameExists = userRepository.existsByUsername(request.username());
        if (userNameExists) {
            log.error("username already exists: {}", request.username());
            throw new BadCredentialsException(USERNAME_EXISTS);
        }
    }

    private boolean isAuthenticated(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
