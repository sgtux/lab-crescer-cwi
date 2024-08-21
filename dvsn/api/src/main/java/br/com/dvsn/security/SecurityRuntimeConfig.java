package br.com.dvsn.security;

import br.com.dvsn.dtos.SecurityRuntimeConfigDto;
import br.com.dvsn.enums.TipoAutenticacao;

public final class SecurityRuntimeConfig {

    private static SecurityRuntimeConfig instance;

    private boolean cookieHttpOnly;

    private boolean cookieSecure;

    private String cookieDomain;

    private int sessionMinutes;

    private TipoAutenticacao tipoAutenticacao;

    private boolean xssPreventionEnabled;

    private boolean xssStoredPreventionEnabled;

    private boolean sqlInjectionPreventionEnabled;

    private SecurityRuntimeConfig() {
        sessionMinutes = 30;
        tipoAutenticacao = TipoAutenticacao.CookieBase64;
    }

    public static SecurityRuntimeConfig getInstance() {
        if (instance == null)
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
        setSessionMinutes(config.getSessionMinutes());
        setTipoAutenticacao(config.getTipoAutenticacao());
        setSqlInjectionPreventionEnabled(config.isSqlInjectionPreventionEnabled());
        setXssPreventionEnabled(config.isXssPreventionEnabled());
        setXssStoredPreventionEnabled(config.isXssStoredPreventionEnabled());
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

    public int getSessionMinutes() {
        return sessionMinutes;
    }

    public void setSessionMinutes(int sessionMinutes) {
        this.sessionMinutes = sessionMinutes;
    }

    public TipoAutenticacao getTipoAutenticacao() {
        return tipoAutenticacao;
    }

    public void setTipoAutenticacao(TipoAutenticacao tipoAutenticacao) {
        this.tipoAutenticacao = tipoAutenticacao;
    }

    public boolean isXssPreventionEnabled() {
        return xssPreventionEnabled;
    }

    public void setXssPreventionEnabled(boolean xssPreventionEnabled) {
        this.xssPreventionEnabled = xssPreventionEnabled;
    }

    public boolean isXssStoredPreventionEnabled() {
        return xssStoredPreventionEnabled;
    }

    public void setXssStoredPreventionEnabled(boolean xssStoredPreventionEnabled) {
        this.xssStoredPreventionEnabled = xssStoredPreventionEnabled;
    }

    public boolean isSqlInjectionPreventionEnabled() {
        return sqlInjectionPreventionEnabled;
    }

    public void setSqlInjectionPreventionEnabled(boolean sqlInjectionPreventionEnabled) {
        this.sqlInjectionPreventionEnabled = sqlInjectionPreventionEnabled;
    }
}
