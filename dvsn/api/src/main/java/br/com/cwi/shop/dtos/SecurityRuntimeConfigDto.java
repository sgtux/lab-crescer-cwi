package br.com.cwi.shop.dtos;

import br.com.cwi.shop.security.SecurityRuntimeConfig;

public class SecurityRuntimeConfigDto {
    private boolean cookieHttpOnly;

    public boolean isCookieHttpOnly() {
        return cookieHttpOnly;
    }

    public void setCookieHttpOnly(boolean cookieHttpOnly) {
        this.cookieHttpOnly = cookieHttpOnly;
    }

    public SecurityRuntimeConfigDto() { }

    public SecurityRuntimeConfigDto(SecurityRuntimeConfig config) {
        cookieHttpOnly = config.isCookieHttpOnly();
    }
}
