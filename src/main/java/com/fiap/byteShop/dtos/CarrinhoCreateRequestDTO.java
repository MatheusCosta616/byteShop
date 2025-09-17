package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO para criação de carrinho. Informe o ID do cliente.",
        example = "{\"clienteId\": 1}"
)
public class CarrinhoCreateRequestDTO {
    @Schema(
            description = "ID do cliente que vai receber o carrinho",
            example = "1"
    )
    private Long clienteId;

    public CarrinhoCreateRequestDTO() {

    }
    public CarrinhoCreateRequestDTO(Long clienteId) {
        this.clienteId = clienteId;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}