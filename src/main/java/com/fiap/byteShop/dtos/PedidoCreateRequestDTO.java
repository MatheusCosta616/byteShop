package com.fiap.byteShop.dtos;

public class PedidoCreateRequestDTO {
    private Long carrinhoId;

    public PedidoCreateRequestDTO() {}
    public PedidoCreateRequestDTO(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }
}
