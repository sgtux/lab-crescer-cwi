package br.com.dvsn.auth.filters;

import br.com.dvsn.auth.UsuarioDetails;
import br.com.dvsn.dtos.UsuarioLogadoDto;
import br.com.dvsn.enums.TipoAutenticacao;
import br.com.dvsn.helpers.Constantes;
import br.com.dvsn.helpers.CookieHelper;
import br.com.dvsn.helpers.StringHelper;
import br.com.dvsn.security.SecurityRuntimeConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CookieBase64AuthenticationFilter extends AuthenticationFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (isFreeEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        var tipoAutenticacao = SecurityRuntimeConfig.getInstance().getTipoAutenticacao();
        if (tipoAutenticacao != TipoAutenticacao.CookieBase64) {
            filterChain.doFilter(request, response);
            return;
        }

        var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);

        if (cookie == null) {
            handleUnauthorized(response, tipoAutenticacao);
            return;
        }

        UsuarioLogadoDto usuarioLogado = null;
        if (SecurityRuntimeConfig.getInstance().isCookieBase64SignatureEnabled()) {
            var arr = cookie.split("\\.");
            if (arr.length != 2) {
                handleUnauthorized(response, "Token com estrutura inválida.");
                return;
            }
            usuarioLogado = CookieHelper.verificarAssinaturaBase64Token(arr[0], arr[1]);
            if (usuarioLogado == null) {
                handleUnauthorized(response, "Assinatura do token está incorreta.");
                return;
            }
        }

        if (usuarioLogado == null) {
            if (cookie.contains(".")) {
                handleUnauthorized(response, "Token com estrutura inválida.");
                return;
            }
            var userJson = StringHelper.fromBase64(cookie);
            usuarioLogado = StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
        }
        var authentication = new UsernamePasswordAuthenticationToken(new UsuarioDetails(usuarioLogado),
                usuarioLogado.getEmail(),
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}