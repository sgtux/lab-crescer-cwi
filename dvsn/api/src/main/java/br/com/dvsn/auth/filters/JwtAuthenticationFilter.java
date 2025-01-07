package br.com.dvsn.auth.filters;

import br.com.dvsn.auth.UsuarioDetails;
import br.com.dvsn.enums.TipoAutenticacao;
import br.com.dvsn.helpers.JwtHelper;
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
public class JwtAuthenticationFilter extends AuthenticationFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (isFreeEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        var tipoAutenticacao = SecurityRuntimeConfig.getInstance().getTipoAutenticacao();
        if(tipoAutenticacao != TipoAutenticacao.Jwt) {
            filterChain.doFilter(request, response);
            return;
        }

        var usuario = JwtHelper.verificarToken(request);

        if (usuario == null) {
            handleUnauthorized(response, tipoAutenticacao);
            return;
        }

        var authentication = new UsernamePasswordAuthenticationToken(new UsuarioDetails(usuario), usuario.getEmail(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}