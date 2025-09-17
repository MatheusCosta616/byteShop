package com.fiap.byteShop.services;

import com.fiap.byteShop.models.Produto;

import java.util.List;

public interface ProdutoService {
    Produto save(ProdutoDTO produto);
    List<ProdutoDTO> getAll();
    ProdutoDTO findById(Long id);
    Produto update(Long id, ProdutoDTO produto);
    void delete(Long id);
}
