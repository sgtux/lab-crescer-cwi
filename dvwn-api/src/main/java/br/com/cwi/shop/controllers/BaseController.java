package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.BadRequestDto;
import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.CookieHelper;
import br.com.cwi.shop.helpers.StringHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected UsuarioLogadoDto obterUsuarioLogado(HttpServletRequest request){
        var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);
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

    protected ResponseEntity<BadRequestDto> badRequest(String erro) {
        return new ResponseEntity(new BadRequestDto(erro), HttpStatus.BAD_REQUEST);
    }
}