package br.com.dvsn.helpers;

import br.com.dvsn.dtos.UsuarioLogadoDto;
import br.com.dvsn.enums.CookieSameSite;
import br.com.dvsn.security.AppConfig;
import br.com.dvsn.security.SecurityRuntimeConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class CookieHelper {

    public static void AddCookie(HttpServletResponse response, String chave, String valor) {

        var config = SecurityRuntimeConfig.getInstance();
        var cookie = new Cookie(chave, valor);
        cookie.setMaxAge(config.getSessionMinutes() * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(config.isCookieHttpOnly());
        cookie.setSecure(config.isCookieSecure());

        var cookieSameSite = config.getCookieSameSite();
        if (cookieSameSite != null && cookieSameSite != CookieSameSite.Empty)
            cookie.setAttribute("SameSite", config.getCookieSameSite().toString());

        if (!StringHelper.isNullOrEmpty(config.getCookieDomain()))
            cookie.setDomain(config.getCookieDomain());

        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String chave) {

        var cookies = request.getCookies();

        if (cookies == null)
            return null;

        for (var c : cookies) {
            if (c.getName().equals(chave))
                return c;
        }
        return null;
    }

    public static void clearCookie(HttpServletResponse response, String chave) {

        var config = SecurityRuntimeConfig.getInstance();

        var cookie = new Cookie(chave, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(config.isCookieHttpOnly());
        cookie.setSecure(config.isCookieSecure());

        if (!StringHelper.isNullOrEmpty(config.getCookieDomain()))
            cookie.setDomain(config.getCookieDomain());

        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String chave) {
        var cookie = getCookie(request, chave);
        return cookie == null ? null : cookie.getValue();
    }

    public static String assinarBase64Token(String base64Token){
        var apiKey = AppConfig.getCookieBase64ApiKey();
        var signatureHash = StringHelper.md5(base64Token + apiKey);
        return base64Token + "." + signatureHash;
    }

    public static UsuarioLogadoDto verificarAssinaturaBase64Token(String base64Token, String signature) {
        var apiKey = AppConfig.getCookieBase64ApiKey();
        var signatureHash = StringHelper.md5(base64Token + apiKey);
        if (!signatureHash.equals(signature))
            return null;
        var userJson = StringHelper.fromBase64(base64Token);
        return StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
    }
}