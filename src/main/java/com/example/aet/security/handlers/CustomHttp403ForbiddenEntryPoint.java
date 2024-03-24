package com.example.aet.security.handlers;

import com.example.aet.security.SecurityHelpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class CustomHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException ex) throws IOException, ServletException {
        log.info("request is: {}", request.getRequestURL());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                "you need to login first in order to perform this action"
        );
        problemDetail.setInstance(new URI(request.getContextPath() + request.getServletPath()));

        response.getWriter().write(SecurityHelpers.toString(problemDetail));
    }
}