package com.fiap.byteShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;
    @Column(nullable = false)
    private Double valor;
    @Column(nullable = false, length = 30)
    private String status;
    @Column(nullable = false, length = 40)
    private String metodo;

    public Pagamento() {
    }

    public Pagamento(Long id, Pedido pedido, Double valor, String status, String metodo) {
        this.id = id;
        this.pedido = pedido;
        this.valor = valor;
        this.status = status;
        this.metodo = metodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
