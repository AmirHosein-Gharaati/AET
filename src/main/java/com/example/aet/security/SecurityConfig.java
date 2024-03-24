package com.example.aet.security;

import com.example.aet.security.filters.JwtAuthenticationFilter;
import com.example.aet.security.handlers.CustomAccessDeniedHandler;
import com.example.aet.security.handlers.CustomHttp403ForbiddenEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpSecurity -> httpSecurity
                        .requestMatchers("/auth/login", "/auth/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(handling -> {
                    handling.accessDeniedHandler(new CustomAccessDeniedHandler());
                    handling.authenticationEntryPoint(new CustomHttp403ForbiddenEntryPoint());
                })

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
