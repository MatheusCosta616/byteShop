package com.fiap.byteShop.dtos;

public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private Double total;
    private String status;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, Long clienteId, Double total, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
        this.status = status;
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
}