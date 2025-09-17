package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.models.Produto;
import com.fiap.byteShop.repositories.ProdutoRepository;
import com.fiap.byteShop.services.ProdutoService;
import com.fiap.byteShop.dtos.ProdutoRequestDTO;
import com.fiap.byteShop.dtos.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public ProdutoResponseDTO save(ProdutoRequestDTO produtoDTO) {
        if (produtoDTO.getNome() == null || produtoDTO.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do produto é obrigatório");
        }
        if (produtoDTO.getPreco() == null || produtoDTO.getPreco() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preço deve ser maior que zero");
        }

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setAtivo(produtoDTO.getAtivo() != null ? produtoDTO.getAtivo() : true);

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    @Override
    public List<ProdutoResponseDTO> getAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return toDTO(produto);
    }

    @Override
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (produtoDTO.getNome() == null || produtoDTO.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do produto é obrigatório");
        }
        if (produtoDTO.getPreco() != null && produtoDTO.getPreco() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preço deve ser maior que zero");
        }

        produto.setNome(produtoDTO.getNome());
        if (produtoDTO.getPreco() != null) {
            produto.setPreco(produtoDTO.getPreco());
        }
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setDescricao(produtoDTO.getDescricao());
        if (produtoDTO.getAtivo() != null) {
            produto.setAtivo(produtoDTO.getAtivo());
        }

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getAtivo()
        );
    }
}