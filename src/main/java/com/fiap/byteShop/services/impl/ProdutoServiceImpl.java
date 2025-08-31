package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.dtos.ProdutoDTO;
import com.fiap.byteShop.models.Produto;
import com.fiap.byteShop.repositories.ProdutoRepository;
import com.fiap.byteShop.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto save(ProdutoDTO produto) {
        Produto newProduto = new Produto();
        newProduto.setNome(produto.getNome());
        newProduto.setPreco(produto.getPreco());
        newProduto.setCategoria(produto.getCategoria());
        newProduto.setDescricao(produto.getDescricao());
        newProduto.setAtivo(produto.getAtivo());

        return produtoRepository.save(newProduto);
    }

    @Override
    public List<ProdutoDTO> getAll() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setId(produto.getId());
                    dto.setNome(produto.getNome());
                    dto.setPreco(produto.getPreco());
                    dto.setCategoria(produto.getCategoria());
                    dto.setDescricao(produto.getDescricao());
                    dto.setAtivo(produto.getAtivo());
                    return dto;
                })
                .toList();
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException(("Produto não encontrado")));
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setDescricao(produto.getDescricao());
        dto.setAtivo(produto.getAtivo());

        return dto;
    }

    @Override
    public Produto update(Long id, ProdutoDTO produto) {
        Produto existingProduto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setCategoria(produto.getCategoria());
        existingProduto.setDescricao(produto.getDescricao());
        existingProduto.setAtivo(produto.getAtivo());
        return produtoRepository.save(existingProduto);
    }

    @Override
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}
