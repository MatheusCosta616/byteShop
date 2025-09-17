package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO para criação de pagamento. Informe o ID do pedido, valor e método de pagamento.",
        example = "{\"pedidoId\": 123, \"valor\": 100.0, \"metodo\": \"PIX\"}"
)
public class PagamentoRequestDTO {
    @Schema(description = "ID do pedido ao qual o pagamento está vinculado", example = "123", required = true)
    private Long pedidoId;

    @Schema(description = "Valor do pagamento (igual ao valor total do pedido)", example = "100.0", required = true)
    private Double valor;

    @Schema(description = "Método de pagamento (PIX, CARTAO, BOLETO)", example = "PIX", required = true)
    private String metodo;

    public PagamentoRequestDTO() {

    }
    public PagamentoRequestDTO(Long pedidoId, Double valor, String metodo) {
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.metodo = metodo;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}