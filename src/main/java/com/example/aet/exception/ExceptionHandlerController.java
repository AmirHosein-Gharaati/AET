package com.example.aet.exception;

import com.example.aet.exception.model.AetException;
import com.example.aet.exception.model.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerController {

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setTitle("Bad credentials");
        return problemDetail;
    }

    @ExceptionHandler(AetException.class)
    public ProblemDetail handleAetException(AetException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail response = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setTitle("Argument Not Valid");

        String errorMessage = ex.getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError fieldError) {
                return fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
            return objectError.getDefaultMessage();
        }).collect(Collectors.joining(", "));
        response.setDetail(errorMessage);
        return response;
    }
}
