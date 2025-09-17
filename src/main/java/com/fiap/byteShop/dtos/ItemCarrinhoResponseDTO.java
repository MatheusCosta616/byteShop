package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO de resposta para um item do carrinho.",
        example = "{\"id\": 10, \"produtoId\": 5, \"nomeProduto\": \"Mouse Gamer\", \"quantidade\": 2, \"precoUnitario\": 125.0, \"subtotal\": 250.0}"
)
public class ItemCarrinhoResponseDTO {
    @Schema(description = "ID do item do carrinho", example = "10")
    private Long id;

    @Schema(description = "ID do produto", example = "5")
    private Long produtoId;

    @Schema(description = "Nome do produto", example = "Mouse Gamer")
    private String nomeProduto;

    @Schema(description = "Quantidade de produto", example = "2")
    private Integer quantidade;

    @Schema(description = "Preço unitário do produto", example = "125.0")
    private Double precoUnitario;

    @Schema(description = "Subtotal do item (preço unitário x quantidade)", example = "250.0")
    private Double subtotal;

    public ItemCarrinhoResponseDTO() {
    }

    public ItemCarrinhoResponseDTO(Long id, Long produtoId, String nomeProduto, Integer quantidade, Double precoUnitario, Double subtotal) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}