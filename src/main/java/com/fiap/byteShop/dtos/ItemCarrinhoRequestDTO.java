package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO para adicionar ou atualizar item no carrinho.",
        example = "{\"produtoId\": 5, \"quantidade\": 2}"
)
public class ItemCarrinhoRequestDTO {
    @Schema(description = "ID do produto a adicionar/atualizar", example = "5")
    private Long produtoId;

    @Schema(description = "Quantidade do produto", example = "2")
    private Integer quantidade;

    public ItemCarrinhoRequestDTO() {

    }
    public ItemCarrinhoRequestDTO(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}