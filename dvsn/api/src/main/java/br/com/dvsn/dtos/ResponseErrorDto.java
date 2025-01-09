package br.com.dvsn.dtos;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseErrorDto extends ResponseEntity<String> {

    private String erro;

    public ResponseErrorDto(String erro, HttpStatusCode httpStatusCode) {
        super(httpStatusCode);
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}