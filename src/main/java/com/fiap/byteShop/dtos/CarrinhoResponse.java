package com.fiap.byteShop.dtos;

import java.util.List;

public class CarrinhoResponse {
    private Long id;
    private Long clienteId;
    private Double total;
    private List<ItemCarrinhoResponse> itens;

    public CarrinhoResponse() {}
    public CarrinhoResponse(Long id, Long clienteId, Double total, List<ItemCarrinhoResponse> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
        this.itens = itens;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public List<ItemCarrinhoResponse> getItens() { return itens; }
    public void setItens(List<ItemCarrinhoResponse> itens) { this.itens = itens; }
}
