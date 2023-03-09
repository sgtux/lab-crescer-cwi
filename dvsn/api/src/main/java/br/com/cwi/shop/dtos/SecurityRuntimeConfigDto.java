package br.com.cwi.shop.dtos;

import br.com.cwi.shop.security.SecurityRuntimeConfig;

public class SecurityRuntimeConfigDto {
    private boolean cookieHttpOnly;

    private boolean cookieSecure;

    private String cookieDomain;

    private int cookieMinutes;

    public SecurityRuntimeConfigDto() { }

    public SecurityRuntimeConfigDto(SecurityRuntimeConfig config) {
        cookieHttpOnly = config.isCookieHttpOnly();
        cookieSecure = config.isCookieSecure();
        cookieDomain = config.getCookieDomain();
        cookieMinutes = config.getCookieMinutes();
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
