package com.fiap.byteShop.dtos;

public class PedidoCreateRequest {
    private Long carrinhoId;

    public PedidoCreateRequest() {}
    public PedidoCreateRequest(Long carrinhoId) { this.carrinhoId = carrinhoId; }
    public Long getCarrinhoId() { return carrinhoId; }
    public void setCarrinhoId(Long carrinhoId) { this.carrinhoId = carrinhoId; }
}
