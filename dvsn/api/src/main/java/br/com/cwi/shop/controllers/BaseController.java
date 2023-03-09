package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.ResponseErrorDto;
import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.CookieHelper;
import br.com.cwi.shop.helpers.StringHelper;
import br.com.cwi.shop.repository.UsuarioRepository;
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

                if(usuarioDb != null)
                    usuarioLogado.setFoto(usuarioDb.getFoto());

                return usuarioLogado;
            } catch (JsonProcessingException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }

    public boolean isAdmin(HttpServletRequest request) {
        var usuario = obterUsuarioLogado(request);
        return usuario.getFuncao() == 1;
    }

    protected ResponseEntity<ResponseErrorDto> forbidden() {
        return new ResponseEntity("Acesso proibido.", HttpStatus.FORBIDDEN);
    }

    protected ResponseEntity<ResponseErrorDto> badRequest(String erro) {
        return new ResponseEntity(new ResponseErrorDto(erro), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ResponseErrorDto> internalServerError(Exception exception) {
        System.err.println(exception);
        return new ResponseEntity(new ResponseErrorDto("Erro interno."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}