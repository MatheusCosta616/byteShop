package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.dtos.ItemPedidoResponseDTO;
import com.fiap.byteShop.dtos.PedidoCreateRequestDTO;
import com.fiap.byteShop.dtos.PedidoResponseDTO;
import com.fiap.byteShop.dtos.PagamentoResponseDTO;
import com.fiap.byteShop.models.Carrinho;
import com.fiap.byteShop.models.ItemCarrinho;
import com.fiap.byteShop.models.ItemPedido;
import com.fiap.byteShop.models.Pedido;
import com.fiap.byteShop.models.Pagamento;
import com.fiap.byteShop.repositories.CarrinhoRepository;
import com.fiap.byteShop.repositories.ItemCarrinhoRepository;
import com.fiap.byteShop.repositories.ItemPedidoRepository;
import com.fiap.byteShop.repositories.PedidoRepository;
import com.fiap.byteShop.repositories.PagamentoRepository;
import com.fiap.byteShop.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final List<String> STATUS_VALIDOS = Arrays.asList("CRIADO", "PAGO", "CANCELADO");

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public PedidoResponseDTO criarPedido(PedidoCreateRequestDTO request) {
        Carrinho carrinho = carrinhoRepository.findById(request.getCarrinhoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));

        List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByCarrinhoId(carrinho.getId());
        if (itensCarrinho.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(carrinho.getCliente());
        pedido.setTotal(carrinho.getTotal());
        pedido.setStatus("CRIADO");
        pedido = pedidoRepository.save(pedido);

        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedido.setPrecoUnitario(itemCarrinho.getPrecoUnitario());
            itemPedido.setSubtotal(itemCarrinho.getSubtotal());
            itemPedidoRepository.save(itemPedido);
        }

        carrinho.setAtivo(false);
        carrinhoRepository.save(carrinho);

        return buscarPedido(pedido.getId());
    }

    @Override
    public List<PedidoResponseDTO> listarPedidos(Long clienteId, String status) {
        List<Pedido> pedidos;
        if (clienteId != null && status != null) {
            pedidos = pedidoRepository.findByClienteIdAndStatus(clienteId, status);
        } else if (clienteId != null) {
            pedidos = pedidoRepository.findByClienteId(clienteId);
        } else if (status != null) {
            pedidos = pedidoRepository.findByStatus(status);
        } else {
            pedidos = pedidoRepository.findAll();
        }
        return pedidos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO buscarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        return toResponse(pedido);
    }

    @Override
    public PedidoResponseDTO atualizarStatus(Long id, String status) {
        if (!STATUS_VALIDOS.contains(status)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido");
        }
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return toResponse(pedido);
    }

    @Override
    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);
    }

    @Override
    public List<ItemPedidoResponseDTO> listarItensPedido(Long pedidoId) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedidoId);
        return itens.stream().map(this::toItemResponse).collect(Collectors.toList());
    }

    private PedidoResponseDTO toResponse(Pedido pedido) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedido.getId());
        List<ItemPedidoResponseDTO> itensResponse = itens.stream().map(this::toItemResponse).collect(Collectors.toList());
        Pagamento pagamento = pagamentoRepository.findByPedidoId(pedido.getId());
        PagamentoResponseDTO pagamentoResponse = null;
        if (pagamento != null) {
            pagamentoResponse = new PagamentoResponseDTO(
                    pagamento.getId(),
                    pagamento.getPedido().getId(),
                    pagamento.getValor(),
                    pagamento.getStatus(),
                    pagamento.getMetodo()
            );
        }
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getTotal(),
                pedido.getStatus(),
                itensResponse,
                pagamentoResponse
        );
    }

    private ItemPedidoResponseDTO toItemResponse(ItemPedido item) {
        return new ItemPedidoResponseDTO(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
        );
    }
}