package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.dtos.PagamentoRequestDTO;
import com.fiap.byteShop.dtos.PagamentoResponseDTO;
import com.fiap.byteShop.dtos.PagamentoStatusUpdateDTO;
import com.fiap.byteShop.models.Pagamento;
import com.fiap.byteShop.models.Pedido;
import com.fiap.byteShop.repositories.PagamentoRepository;
import com.fiap.byteShop.repositories.PedidoRepository;
import com.fiap.byteShop.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private static final List<String> STATUS_VALIDOS = Arrays.asList("PENDENTE", "APROVADO", "RECUSADO");
    private static final List<String> METODOS_PAGAMENTO = Arrays.asList("PIX", "CARTAO", "BOLETO");

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO request) {
        Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Pagamento pagamentoExistente = pagamentoRepository.findByPedidoId(pedido.getId());
        if (pagamentoExistente != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe pagamento para esse pedido.");
        }

        if (request.getValor() == null || !request.getValor().equals(pedido.getTotal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor do pagamento deve ser igual ao total do pedido.");
        }

        String metodo = request.getMetodo() != null ? request.getMetodo().toUpperCase() : null;
        if (metodo == null || !METODOS_PAGAMENTO.contains(metodo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Método de pagamento inválido.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValor(request.getValor());
        pagamento.setMetodo(metodo);
        pagamento.setStatus("PENDENTE");

        pagamento = pagamentoRepository.save(pagamento);

        return toResponse(pagamento);
    }

    @Override
    public PagamentoResponseDTO buscarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
        return toResponse(pagamento);
    }

    @Override
    public PagamentoResponseDTO atualizarStatus(Long id, PagamentoStatusUpdateDTO statusUpdate) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));

        String novoStatus = statusUpdate.getStatus() != null ? statusUpdate.getStatus().toUpperCase() : null;
        if (novoStatus == null || !STATUS_VALIDOS.contains(novoStatus)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status de pagamento inválido.");
        }

        pagamento.setStatus(novoStatus);
        pagamento = pagamentoRepository.save(pagamento);

        return toResponse(pagamento);
    }

    private PagamentoResponseDTO toResponse(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getPedido().getId(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getMetodo()
        );
    }
}