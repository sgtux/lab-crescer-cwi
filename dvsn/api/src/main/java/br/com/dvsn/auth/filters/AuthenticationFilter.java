package br.com.dvsn.auth.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dvsn.enums.TipoAutenticacao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AuthenticationFilter extends OncePerRequestFilter {

    protected boolean isFreeEndpoint(HttpServletRequest request) {
        String endpoint = request.getRequestURI().toString();
        return endpoint.equals("/favicon.ico")
                || endpoint.equals("/index.html")
                || endpoint.equals("/")
                || endpoint.startsWith("/auth/")
                || endpoint.startsWith("/static/")
                || endpoint.startsWith("/fontawesome/")
                || endpoint.startsWith("/image/");
    }

    protected void handleUnauthorized(HttpServletResponse response, TipoAutenticacao tipoAutenticacao)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \" Autenticação é necessária (" + tipoAutenticacao + ") \"}");
    }
}