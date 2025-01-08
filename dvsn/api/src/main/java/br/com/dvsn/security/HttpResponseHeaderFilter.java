package br.com.dvsn.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dvsn.enums.XFrameOptionsHeader;
import br.com.dvsn.helpers.StringHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpResponseHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var config = SecurityRuntimeConfig.getInstance();

        xFrameOptionsHeader(config, response);
        contentSecurityPolicy(config, response);

        filterChain.doFilter(request, response);
    }

    private void xFrameOptionsHeader(SecurityRuntimeConfig config, HttpServletResponse response) {
        var xFrameOptionsHeader = config.getxFrameOptionsHeader();

        if (xFrameOptionsHeader == XFrameOptionsHeader.Deny)
            response.addHeader("X-FRAME-OPTIONS", "DENY");

        if (xFrameOptionsHeader == XFrameOptionsHeader.SameOrigin)
            response.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
    }

    private void contentSecurityPolicy(SecurityRuntimeConfig config, HttpServletResponse response) {
        var contentSecurityPolicy = config.getContentSecurityPolicy();
        if (!StringHelper.isNullOrEmpty(contentSecurityPolicy))
            response.addHeader("Content-Security-Policy", contentSecurityPolicy);
    }
}