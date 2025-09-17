package com.fiap.byteShop.dtos;

public class CarrinhoDTO {
    private Long id;
    private String nome;
    private String email;
    private String documento;

    public CarrinhoDTO() {
    }

    public CarrinhoDTO(Long id, String nome, String email, String documento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
