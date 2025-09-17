package com.fiap.byteShop.dtos;

public class ProdutoRequestDTO {
    private String nome;
    private Double preco;
    private String categoria;
    private String descricao;
    private Boolean ativo;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(String nome, Double preco, String categoria, String descricao, Boolean ativo) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Boolean getActivo() { return ativo; }
    public void setActivo(Boolean ativo) { this.ativo = ativo; }
}
