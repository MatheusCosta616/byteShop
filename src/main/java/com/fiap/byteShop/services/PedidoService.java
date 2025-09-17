package com.fiap.byteShop.services;

import com.fiap.byteShop.dtos.PedidoCreateRequestDTO;
import com.fiap.byteShop.dtos.PedidoResponseDTO;
import com.fiap.byteShop.dtos.ItemPedidoResponseDTO;
import java.util.List;

public interface PedidoService {
    PedidoResponseDTO criarPedido(PedidoCreateRequestDTO request);
    List<PedidoResponseDTO> listarPedidos(Long clienteId, String status);
    PedidoResponseDTO buscarPedido(Long id);
    PedidoResponseDTO atualizarStatus(Long id, String status);
    void cancelarPedido(Long id);
    List<ItemPedidoResponseDTO> listarItensPedido(Long pedidoId);
}