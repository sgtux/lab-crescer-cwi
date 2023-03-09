package br.com.cwi.shop.auth.filters;

import br.com.cwi.shop.auth.UsuarioDetails;
import br.com.cwi.shop.enums.TipoAutenticacao;
import br.com.cwi.shop.helpers.JwtHelper;
import br.com.cwi.shop.security.SecurityRuntimeConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if(SecurityRuntimeConfig.getInstance().getTipoAutenticacao() != TipoAutenticacao.Jwt) {
            filterChain.doFilter(request, response);
            return;
        }

        var usuario = JwtHelper.verificarToken(request);

        if (usuario == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var authentication = new UsernamePasswordAuthenticationToken(new UsuarioDetails(usuario), usuario.getEmail(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}