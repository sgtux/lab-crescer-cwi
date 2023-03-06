package br.com.cwi.shop.auth;

import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.CookieHelper;
import br.com.cwi.shop.helpers.StringHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class CookieBase64AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var cookie = CookieHelper.getCookieValue(request, Constantes.AUTH_COOKIE_NAME);

        if (cookie == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var userJson = StringHelper.fromBase64(cookie);
            var usuario = StringHelper.fromJson(userJson, UsuarioLogadoDto.class);
            var authentication = new UsernamePasswordAuthenticationToken(new UsuarioDetails(usuario), usuario.getEmail(), new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
