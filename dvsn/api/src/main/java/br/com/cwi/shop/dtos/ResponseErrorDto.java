package br.com.cwi.shop.dtos;

public class ResponseErrorDto {

    private String erro;

    public ResponseErrorDto(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
