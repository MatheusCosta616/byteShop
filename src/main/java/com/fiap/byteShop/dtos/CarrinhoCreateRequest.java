package com.fiap.byteShop.dtos;

public class CarrinhoCreateRequest {
    private Long clienteId;

    public CarrinhoCreateRequest() {}
    public CarrinhoCreateRequest(Long clienteId) { this.clienteId = clienteId; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}
