package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(
        description = "DTO de resposta do carrinho, incluindo resumo e itens.",
        example = "{\"id\": 1, \"clienteId\": 1, \"total\": 250.0, \"itens\": [{\"id\": 10, \"produtoId\": 5, \"nomeProduto\": \"Mouse Gamer\", \"quantidade\": 2, \"precoUnitario\": 125.0, \"subtotal\": 250.0}]}"
)
public class CarrinhoResponseDTO {
    @Schema(description = "ID do carrinho", example = "1")
    private Long id;

    @Schema(description = "ID do cliente dono do carrinho", example = "1")
    private Long clienteId;

    @Schema(description = "Total do carrinho", example = "250.0")
    private Double total;

    @Schema(description = "Lista de itens presentes no carrinho")
    private List<ItemCarrinhoResponseDTO> itens;

    public CarrinhoResponseDTO() {

    }
    public CarrinhoResponseDTO(Long id, Long clienteId, Double total, List<ItemCarrinhoResponseDTO> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemCarrinhoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoResponseDTO> itens) {
        this.itens = itens;
    }
}