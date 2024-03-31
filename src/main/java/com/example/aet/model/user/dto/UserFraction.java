package com.example.aet.model.user.dto;

import com.example.aet.model.user.User;

public record UserFraction(
        String id,
        String username
) {
    public UserFraction(User user) {
        this(
                user.getId(),
                user.getUsername()
        );
    }
}
