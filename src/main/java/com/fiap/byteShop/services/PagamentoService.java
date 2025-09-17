package com.fiap.byteShop.services;

import com.fiap.byteShop.dtos.PagamentoRequestDTO;
import com.fiap.byteShop.dtos.PagamentoResponseDTO;
import com.fiap.byteShop.dtos.PagamentoStatusUpdateDTO;

public interface PagamentoService {
    PagamentoResponseDTO criarPagamento(PagamentoRequestDTO request);
    PagamentoResponseDTO buscarPagamento(Long id);
    PagamentoResponseDTO atualizarStatus(Long id, PagamentoStatusUpdateDTO statusUpdate);
}