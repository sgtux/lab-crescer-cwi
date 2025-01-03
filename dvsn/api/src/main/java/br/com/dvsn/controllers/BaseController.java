package br.com.dvsn.controllers;

import br.com.dvsn.dtos.ResponseErrorDto;
import br.com.dvsn.dtos.UsuarioLogadoDto;
import br.com.dvsn.enums.TipoAutenticacao;
import br.com.dvsn.helpers.*;
import br.com.dvsn.repository.SessaoRepository;
import br.com.dvsn.repository.UsuarioRepository;
import br.com.dvsn.security.SecurityRuntimeConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class BaseController {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected SessaoRepository sessaoRepository;

    protected UsuarioLogadoDto obterUsuarioLogado(HttpServletRequest request) {

        var tipoAutenticacao = SecurityRuntimeConfig.getInstance().getTipoAutenticacao();
        if (tipoAutenticacao == TipoAutenticacao.Jwt) {
            var usuario = JwtHelper.verificarToken(request);
            return new UsuarioLogadoDto(usuario);
        }

        if (tipoAutenticacao == TipoAutenticacao.CookieBase64) {
            var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);

            if (cookie != null) {
                var userJson = StringHelper.fromBase64(cookie);
                var usuarioLogado = StringHelper.fromJson(userJson, UsuarioLogadoDto.class);

                var usuarioDb = usuarioRepository.buscarPorId(usuarioLogado.getId());

                if (usuarioDb != null)
                    usuarioLogado.setFoto(usuarioDb.getFoto());

                return usuarioLogado;
            }
        }

        if (tipoAutenticacao == TipoAutenticacao.TokenOpaco) {
            var sessao = TokenOpacoHelper.verificarSessao(request, sessaoRepository);
            var usuario = usuarioRepository.buscarPorId(sessao.getUsuarioId());
            return new UsuarioLogadoDto(usuario);
        }

        return null;
    }

    public boolean isAdmin(HttpServletRequest request) {
        var usuario = obterUsuarioLogado(request);
        return usuario.getFuncao() == 1;
    }

    protected ResponseErrorDto unauthorized(String message) {
        return new ResponseErrorDto(message, HttpStatus.UNAUTHORIZED);
    }

    protected ResponseErrorDto forbidden() {
        return new ResponseErrorDto("Acesso proibido.", HttpStatus.FORBIDDEN);
    }

    protected ResponseErrorDto badRequest(String erro) {
        return new ResponseErrorDto(erro, HttpStatus.BAD_REQUEST);
    }

    protected ResponseErrorDto internalServerError(Exception exception) {
        System.err.println(exception);
        return new ResponseErrorDto("Erro interno.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}