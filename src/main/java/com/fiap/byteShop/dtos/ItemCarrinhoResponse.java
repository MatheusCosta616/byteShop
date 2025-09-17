package com.fiap.byteShop.dtos;

public class ItemCarrinhoResponse {
    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;

    public ItemCarrinhoResponse() {}
    public ItemCarrinhoResponse(Long id, Long produtoId, String nomeProduto, Integer quantidade, Double precoUnitario, Double subtotal) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
