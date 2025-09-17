package com.fiap.byteShop.services;

import com.fiap.byteShop.dtos.ProdutoRequestDTO;
import com.fiap.byteShop.dtos.ProdutoResponseDTO;
import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO save(ProdutoRequestDTO produto);
    List<ProdutoResponseDTO> getAll();
    ProdutoResponseDTO findById(Long id);
    ProdutoResponseDTO update(Long id, ProdutoRequestDTO produto);
    void delete(Long id);
}
