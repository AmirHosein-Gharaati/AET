package com.example.aet.security.filters;

import com.example.aet.model.auth.AetPrincipal;
import com.example.aet.model.user.User;
import com.example.aet.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            @Qualifier("userDetails") UserDetailsService userDetailsService,
            JwtService jwtService
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        Optional<String> jwtOptional = jwtService.extractJwtToken(request);

        if (jwtOptional.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = jwtOptional.get();
        final String username = jwtService.extractUsername(jwt);

        log.debug("username: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = (User) this.userDetailsService.loadUserByUsername(username);
            AetPrincipal principal = new AetPrincipal(user);

            if (jwtService.isValidToken(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        List.of()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
