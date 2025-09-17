package com.fiap.byteShop.dtos;

public class PagamentoStatusUpdate {
    private String status;

    public PagamentoStatusUpdate() {}
    public PagamentoStatusUpdate(String status) { this.status = status; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
