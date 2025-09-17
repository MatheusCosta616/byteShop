package com.fiap.byteShop.dtos;

public class PagamentoRequest {
    private Long pedidoId;
    private Double valor;
    private String metodo;

    public PagamentoRequest() {}
    public PagamentoRequest(Long pedidoId, Double valor, String metodo) {
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.metodo = metodo;
    }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
}
