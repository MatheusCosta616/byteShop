package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO para atualizar o status de um pagamento.",
        example = "{\"status\": \"APROVADO\"}"
)
public class PagamentoStatusUpdateDTO {
    @Schema(description = "Novo status para o pagamento (PENDENTE, APROVADO, RECUSADO)", example = "APROVADO", required = true)
    private String status;

    public PagamentoStatusUpdateDTO() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}