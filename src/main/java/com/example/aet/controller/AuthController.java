package com.example.aet.controller;

import com.example.aet.model.auth.AetPrincipal;
import com.example.aet.model.user.dto.AuthenticationDetail;
import com.example.aet.model.user.dto.LoginRequest;
import com.example.aet.model.user.dto.SignUpRequest;
import com.example.aet.model.user.dto.UserFraction;
import com.example.aet.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthenticationDetail login(@RequestBody @Valid LoginRequest request) {
        log.info("login with username: {}", request.username());
        return authService.login(request);
    }

    @PostMapping("/signup")
    public AuthenticationDetail signup(@RequestBody @Valid SignUpRequest request) {
        log.info("signup with username: {}", request.username());
        return authService.signup(request);
    }

    @GetMapping("/whoami")
    public UserFraction whoami(
            @AuthenticationPrincipal AetPrincipal principal
    ) {
        log.info("whoami with user {}", principal.getId());
        return authService.whoami(principal.getId());
    }
}
