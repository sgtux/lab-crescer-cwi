package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.CookieHelper;
import br.com.cwi.shop.helpers.StringHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

    protected UsuarioLogadoDto obterUsuarioLogado(HttpServletRequest request){
        var cookie = CookieHelper.getCookie(request, Constantes.AUTH_COOKIE_NAME);
        if(cookie != null) {
            try {
                var userJson = StringHelper.fromBase64(cookie);
                return StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
            } catch (JsonProcessingException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }
}