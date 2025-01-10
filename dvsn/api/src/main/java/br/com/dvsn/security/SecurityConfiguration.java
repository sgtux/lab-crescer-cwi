package br.com.dvsn.security;

import br.com.dvsn.auth.filters.CookieBase64AuthenticationFilter;
import br.com.dvsn.auth.filters.JwtAuthenticationFilter;
import br.com.dvsn.auth.filters.TokenOpacoAuthenticationFilter;
import br.com.dvsn.handlers.ExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CookieBase64AuthenticationFilter cookieBase64AuthenticationFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final TokenOpacoAuthenticationFilter tokenOpacoAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    private final HttpResponseHeaderFilter httpResponseHeaderFilter;

    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**",
                        "/",
                        "/favicon.ico",
                        "/csp/**",
                        "/index.html",
                        "/static/**",
                        "/fontawesome/**",
                        "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers().disable()
                .cors().disable()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(cookieBase64AuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, CookieBase64AuthenticationFilter.class)
                .addFilterBefore(tokenOpacoAuthenticationFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(httpResponseHeaderFilter, TokenOpacoAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, HttpResponseHeaderFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN));

        return http.build();
    }
}