package br.com.cwi.shop.security;

import br.com.cwi.shop.dtos.SecurityRuntimeConfigDto;

public final class SecurityRuntimeConfig {

    private static SecurityRuntimeConfig instance;

    private boolean cookieHttpOnly;

    private SecurityRuntimeConfig() {
        cookieHttpOnly = true;
    }

    public static SecurityRuntimeConfig getInstance() {
        if(instance == null)
            instance = new SecurityRuntimeConfig();
        return instance;
    }

    public boolean isCookieHttpOnly() {
        return cookieHttpOnly;
    }

    public void setCookieHttpOnly(boolean cookieHttpOnly) {
        this.cookieHttpOnly = cookieHttpOnly;
    }

    public void update(SecurityRuntimeConfigDto config) {
        setCookieHttpOnly(config.isCookieHttpOnly());
    }
}
