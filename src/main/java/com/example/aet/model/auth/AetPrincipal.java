package com.example.aet.model.auth;

import com.example.aet.model.user.User;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.security.Principal;

@Getter
public class AetPrincipal implements Principal {
    private final String id;
    private final String username;

    public AetPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    @Override
    public String getName() {
        if (StringUtils.isEmpty(this.username)) return "unknown";
        return this.username;
    }

}
