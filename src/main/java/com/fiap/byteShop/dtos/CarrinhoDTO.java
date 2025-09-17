package com.fiap.byteShop.dtos;

public class CarrinhoDTO {
    private Long id;
    private Long clienteId;

    public CarrinhoDTO() {
    }

    public CarrinhoDTO(Long id, Long clienteId) {
        this.id = id;
        this.clienteId = clienteId;
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
}
