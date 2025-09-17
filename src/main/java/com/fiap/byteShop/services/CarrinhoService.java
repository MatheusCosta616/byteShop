package com.fiap.byteShop.services;

import com.fiap.byteShop.dtos.CarrinhoCreateRequestDTO;
import com.fiap.byteShop.dtos.CarrinhoResponseDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoRequestDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoResponseDTO;
import java.util.List;

public interface CarrinhoService {
    CarrinhoResponseDTO criarCarrinho(CarrinhoCreateRequestDTO request);
    CarrinhoResponseDTO buscarCarrinho(Long id);
    void deletarCarrinho(Long id);
    ItemCarrinhoResponseDTO adicionarItem(Long carrinhoId, ItemCarrinhoRequestDTO item);
    ItemCarrinhoResponseDTO atualizarItem(Long itemId, ItemCarrinhoRequestDTO item);
    void removerItem(Long itemId);
    List<ItemCarrinhoResponseDTO> listarItens(Long carrinhoId);
}