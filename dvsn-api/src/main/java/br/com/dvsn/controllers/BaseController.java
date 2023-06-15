package br.com.dvsn.controllers;

import br.com.dvsn.dtos.ResponseErrorDto;
import br.com.dvsn.dtos.UsuarioLogadoDto;
import br.com.dvsn.helpers.Constantes;
import br.com.dvsn.helpers.CookieHelper;
import br.com.dvsn.helpers.StringHelper;
import br.com.dvsn.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    protected UsuarioLogadoDto obterUsuarioLogado(HttpServletRequest request){
        var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);
        if(cookie != null) {
            try {
                var userJson = StringHelper.fromBase64(cookie);
                var usuarioLogado = StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
                var usuarioDb = usuarioRepository.buscarPorId(usuarioLogado.getId());

                if(usuarioDb == null)
                    return null;

                return new UsuarioLogadoDto(usuarioDb);
            } catch (JsonProcessingException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }

    protected ResponseEntity<ResponseErrorDto> badRequest(String erro) {
        return new ResponseEntity(new ResponseErrorDto(erro), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ResponseErrorDto> internalServerError(Exception exception) {
        System.err.println(exception);
        return new ResponseEntity(new ResponseErrorDto("Erro interno."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}