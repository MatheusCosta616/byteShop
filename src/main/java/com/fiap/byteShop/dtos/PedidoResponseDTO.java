package com.fiap.byteShop.dtos;

import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private Long clienteId;
    private Double total;
    private String status;
    private List<ItemPedidoResponseDTO> itens;
    private PagamentoResponseDTO pagamento;

    public PedidoResponseDTO() {}
    public PedidoResponseDTO(Long id, Long clienteId, Double total, String status, List<ItemPedidoResponseDTO> itens, PagamentoResponseDTO pagamento) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
        this.status = status;
        this.itens = itens;
        this.pagamento = pagamento;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedidoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponseDTO> itens) {
        this.itens = itens;
    }

    public PagamentoResponseDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoResponseDTO pagamento) {
        this.pagamento = pagamento;
    }
}
