package br.com.dvsn.dtos;

import br.com.dvsn.enums.CookieSameSite;
import br.com.dvsn.enums.TipoAutenticacao;
import br.com.dvsn.enums.XFrameOptionsHeader;
import br.com.dvsn.security.SecurityRuntimeConfig;

public class SecurityRuntimeConfigDto {

    private boolean cookieHttpOnly;

    private boolean cookieSecure;

    private String cookieDomain;

    private CookieSameSite cookieSameSite;

    private int sessionMinutes;

    private TipoAutenticacao tipoAutenticacao;

    private boolean xssPreventionEnabled;

    private boolean xssStoredPreventionEnabled;

    private boolean sqlInjectionPreventionEnabled;

    private boolean csrfTokenEnabled;

    private XFrameOptionsHeader xFrameOptionsHeader;

    private String contentSecurityPolicy;

    private String cors;

    public SecurityRuntimeConfigDto() {
    }

    public SecurityRuntimeConfigDto(SecurityRuntimeConfig config) {
        cookieHttpOnly = config.isCookieHttpOnly();
        cookieSecure = config.isCookieSecure();
        cookieDomain = config.getCookieDomain();
        cookieSameSite = config.getCookieSameSite();
        sessionMinutes = config.getSessionMinutes();
        tipoAutenticacao = config.getTipoAutenticacao();
        xssPreventionEnabled = config.isXssPreventionEnabled();
        xssStoredPreventionEnabled = config.isXssStoredPreventionEnabled();
        sqlInjectionPreventionEnabled = config.isSqlInjectionPreventionEnabled();
        csrfTokenEnabled = config.isCsrfTokenEnabled();
        xFrameOptionsHeader = config.getxFrameOptionsHeader();
        contentSecurityPolicy = config.getContentSecurityPolicy();
        cors = config.getCors();
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

    public CookieSameSite getCookieSameSite() {
        return this.cookieSameSite;
    }

    public void setCookieSameSite(CookieSameSite cookieSameSite) {
        this.cookieSameSite = cookieSameSite;
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

    public boolean isCsrfTokenEnabled() {
        return this.csrfTokenEnabled;
    }

    public void setCsrfTokenEnabled(boolean csrfTokenEnabled) {
        this.csrfTokenEnabled = csrfTokenEnabled;
    }

    public XFrameOptionsHeader getxFrameOptionsHeader() {
        return xFrameOptionsHeader;
    }

    public void setxFrameOptionsHeader(XFrameOptionsHeader xFrameOptionsHeader) {
        this.xFrameOptionsHeader = xFrameOptionsHeader;
    }

    public String getContentSecurityPolicy() {
        return contentSecurityPolicy;
    }

    public void setContentSecurityPolicy(String contentSecurityPolicy) {
        this.contentSecurityPolicy = contentSecurityPolicy;
    }

    public String getCors() {
        return this.cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }
}