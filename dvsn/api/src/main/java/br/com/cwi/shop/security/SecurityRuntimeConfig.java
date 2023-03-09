package br.com.cwi.shop.security;

import br.com.cwi.shop.dtos.SecurityRuntimeConfigDto;

public final class SecurityRuntimeConfig {

    private static SecurityRuntimeConfig instance;

    private boolean cookieHttpOnly;

    private boolean cookieSecure;

    private String cookieDomain;

    private int cookieMinutes;

    private SecurityRuntimeConfig() {
        cookieMinutes = 20;
    }

    public static SecurityRuntimeConfig getInstance() {
        if(instance == null)
            instance = new SecurityRuntimeConfig();
        return instance;
    }

    public static void reset() {
        instance = new SecurityRuntimeConfig();
    }

    public void update(SecurityRuntimeConfigDto config) {
        setCookieHttpOnly(config.isCookieHttpOnly());
        setCookieDomain(config.getCookieDomain());
        setCookieSecure(config.isCookieSecure());
        setCookieMinutes(config.getCookieMinutes());
    }

    public boolean isCookieHttpOnly() {
        return cookieHttpOnly;
    }

    public void setCookieHttpOnly(boolean cookieHttpOnly) {
        this.cookieHttpOnly = cookieHttpOnly;
    }

    public boolean isCookieSecure() {
        return cookieSecure;
    }

    public void setCookieSecure(boolean cookieSecure) {
        this.cookieSecure = cookieSecure;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public int getCookieMinutes() {
        return cookieMinutes;
    }

    public void setCookieMinutes(int cookieMinutes) {
        this.cookieMinutes = cookieMinutes;
    }
}
