package br.com.cwi.shop.helpers;

import br.com.cwi.shop.security.SecurityRuntimeConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class CookieHelper {

    public static void AddCookie(HttpServletResponse response, String chave, String valor, int segundos){

        var config = SecurityRuntimeConfig.getInstance();

        var cookie = new Cookie(chave, valor);
        cookie.setMaxAge(segundos);
        cookie.setPath("/");
        cookie.setHttpOnly(config.isCookieHttpOnly());
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String chave){

        var cookies = request.getCookies();

        if(cookies == null)
            return null;

        for (var c : cookies) {
            if(c.getName().equals(chave))
                return c;
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String chave) {
        var cookie = getCookie(request, chave);
        return cookie == null ? null : cookie.getValue();
    }

    public static void removeCookie(HttpServletRequest request, String chave) {
        var cookie = getCookie(request, chave);
        cookie.setMaxAge(0);
    }
}
