package br.com.cwi.shop.dtos;

import jakarta.persistence.Column;

import java.util.Date;

public class UsuarioDto {

    private Long id;

    private String username;

    private String email;

    private String senha;

    private Date criadoEm;

    private String token;

    private Date tokenExpiraEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiraEm() {
        return tokenExpiraEm;
    }

    public void setTokenExpiraEm(Date tokenExpiraEm) {
        this.tokenExpiraEm = tokenExpiraEm;
    }
}
