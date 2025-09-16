package com.fiap.byteShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "preco_unitario", nullable = false)
    private Float precoUnitario;

    public ItemCarrinho() {
    }

    public ItemCarrinho(Long id, Carrinho carrinho, Produto produto, Integer quantidade, Float precoUnitario) {
        this.id = id;
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
