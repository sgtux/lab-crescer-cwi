package br.com.cwi.shop.helpers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class CookieHelper {

    public static void AddCookie(HttpServletResponse response, String chave, String valor, int segundos){
        var cookie = new Cookie(chave, valor);
        cookie.setMaxAge(segundos);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String chave){

        var cookies = request.getCookies();

        if(cookies == null)
            return null;

        for (var c : cookies) {
            if(c.getName().equals(chave))
                return c.getValue();
        }
        return null;
    }
}
