package com.example.aet.security;

import com.example.aet.exception.model.AetException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityHelpers {

    private SecurityHelpers() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AetException(e.getMessage());
        }
    }
}
