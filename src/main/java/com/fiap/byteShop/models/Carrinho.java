package com.fiap.byteShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "ativo")
    private Boolean ativo;

    public Carrinho() {
        this.total = 0.0;
        this.ativo = true;
    }

    public Carrinho(Long id, Cliente cliente, Double total, Boolean ativo) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
