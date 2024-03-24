package com.example.aet.security.handlers;

import com.example.aet.security.SecurityHelpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @SneakyThrows
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException ex) throws IOException, ServletException {
        log.info("request is: {}", request.getRequestURL());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                "you don't have required role to perform this action"
        );
        problemDetail.setInstance(new URI(request.getContextPath() + request.getServletPath()));

        response.getWriter().write(SecurityHelpers.toString(problemDetail));
    }

}