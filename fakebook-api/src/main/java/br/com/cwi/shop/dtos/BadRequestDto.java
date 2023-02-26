package br.com.cwi.shop.dtos;

public class BadRequestDto {

    private String erro;

    public BadRequestDto(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
