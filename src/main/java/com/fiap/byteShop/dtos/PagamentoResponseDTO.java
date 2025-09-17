package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO de resposta do pagamento, contendo informações detalhadas.",
        example = "{\"id\": 10, \"pedidoId\": 123, \"valor\": 100.0, \"status\": \"PENDENTE\", \"metodo\": \"PIX\"}"
)
public class PagamentoResponseDTO {
    @Schema(description = "ID do pagamento", example = "10")
    private Long id;

    @Schema(description = "ID do pedido relacionado ao pagamento", example = "123")
    private Long pedidoId;

    @Schema(description = "Valor do pagamento", example = "100.0")
    private Double valor;

    @Schema(description = "Status do pagamento (PENDENTE, APROVADO, RECUSADO)", example = "PENDENTE")
    private String status;

    @Schema(description = "Método utilizado para o pagamento", example = "PIX")
    private String metodo;

    public PagamentoResponseDTO() {

    }
    public PagamentoResponseDTO(Long id, Long pedidoId, Double valor, String status, String metodo) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.status = status;
        this.metodo = metodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}