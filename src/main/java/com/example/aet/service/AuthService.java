package com.example.aet.service;

import com.example.aet.model.user.User;
import com.example.aet.model.user.dto.LoginRequest;
import com.example.aet.model.user.dto.AuthenticationDetail;
import com.example.aet.repository.UserRepository;
import com.example.aet.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.aet.exception.Messages.INVALID_USERNAME_OR_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationDetail login(LoginRequest request) {
        User user = userRepository.findByUserName(request.username())
                .orElseThrow(() -> new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD));

        if (!isAuthenticated(user, request.password())) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
        }

        String token = jwtService.generateToken(user);

        return new AuthenticationDetail(token);
    }

    private boolean isAuthenticated(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
