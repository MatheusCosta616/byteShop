package com.fiap.byteShop.dtos;

public class PagamentoResponse {
    private Long id;
    private Long pedidoId;
    private Double valor;
    private String status;
    private String metodo;

    public PagamentoResponse() {}
    public PagamentoResponse(Long id, Long pedidoId, Double valor, String status, String metodo) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.status = status;
        this.metodo = metodo;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
}
