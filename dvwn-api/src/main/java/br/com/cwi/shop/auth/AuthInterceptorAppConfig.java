package br.com.cwi.shop.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthInterceptorAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthCookieBase64Interceptor())
                .excludePathPatterns(
                        "/index.html",
                        "/",
                        "/token",
                        "/criarConta",
                        "/js/**",
                        "/favico.ico",
                        "/css/**"
                );
    }
}