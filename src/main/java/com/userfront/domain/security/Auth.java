package com.userfront.domain.security;

import org.springframework.security.core.GrantedAuthority;

public class Auth implements GrantedAuthority{

    private final String authority;

    public Auth(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
