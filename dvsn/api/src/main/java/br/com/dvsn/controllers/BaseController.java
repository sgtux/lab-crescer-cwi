package br.com.dvsn.controllers;

import br.com.dvsn.dtos.UsuarioLogadoDto;
import br.com.dvsn.enums.TipoAutenticacao;
import br.com.dvsn.helpers.*;
import br.com.dvsn.repository.SessaoRepository;
import br.com.dvsn.repository.UsuarioRepository;
import br.com.dvsn.security.SecurityRuntimeConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    protected ResponseEntity<?> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse(message));
    }

    protected ResponseEntity<?> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse("Acesso proibido."));
    }

    protected ResponseEntity<?> badRequest(String erro) {
        return ResponseEntity.badRequest().body(errorResponse(erro));
    }

    protected ResponseEntity<?> notFound(String erro) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(erro));
    }

    protected ResponseEntity<?> internalServerError(Exception exception) {
        System.err.println(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Erro interno."));
    }

    private Object errorResponse(String erro) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erro", erro);
        return errorResponse;
    }
}