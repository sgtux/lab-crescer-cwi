package br.com.dvsn.security;

import java.util.UUID;

public class CsrfToken {

    private String token;

    private long userId;

    public CsrfToken(long userId) {
        this.token = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public long getUserId() {
        return userId;
    }
}