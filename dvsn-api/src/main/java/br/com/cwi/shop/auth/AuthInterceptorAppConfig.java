package br.com.cwi.shop.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthInterceptorAppConfig implements WebMvcConfigurer {

    private String[] authenticatedRoutes = new String[] {
            "/comentario",
            "/comentario/**",
            "/usuario",
            "/usuarios",
            "/post"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthCookieBase64Interceptor())
                .addPathPatterns(authenticatedRoutes);
    }
}