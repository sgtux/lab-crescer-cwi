package br.com.cwi.shop.auth;

import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.StringHelper;
import br.com.cwi.shop.helpers.CookieHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthCookieBase64Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);
        if(cookie != null) {
            try {
                var userJson = StringHelper.fromBase64(cookie);
                StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
                return true;
            } catch (JsonProcessingException ex) {
                System.out.println(ex);
            }
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        return false;
    }
}
