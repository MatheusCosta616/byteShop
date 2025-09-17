package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.dtos.CarrinhoCreateRequestDTO;
import com.fiap.byteShop.dtos.CarrinhoResponseDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoRequestDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoResponseDTO;
import com.fiap.byteShop.models.Carrinho;
import com.fiap.byteShop.models.Cliente;
import com.fiap.byteShop.models.ItemCarrinho;
import com.fiap.byteShop.models.Produto;
import com.fiap.byteShop.repositories.CarrinhoRepository;
import com.fiap.byteShop.repositories.ClienteRepository;
import com.fiap.byteShop.repositories.ItemCarrinhoRepository;
import com.fiap.byteShop.repositories.ProdutoRepository;
import com.fiap.byteShop.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public CarrinhoResponseDTO criarCarrinho(CarrinhoCreateRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Carrinho existente = carrinhoRepository.findByClienteIdAndAtivo(cliente.getId(), true);
        if (existente != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente já possui um carrinho ativo");
        }

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(cliente);
        carrinho.setTotal(0.0);
        carrinho.setAtivo(true);
        carrinho = carrinhoRepository.save(carrinho);
        return toResponse(carrinho);
    }

    @Override
    public CarrinhoResponseDTO buscarCarrinho(Long id) {
        Carrinho carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));
        return toResponse(carrinho);
    }

    @Override
    public void deletarCarrinho(Long id) {
        Carrinho carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));
        List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinhoId(carrinho.getId());
        itemCarrinhoRepository.deleteAll(itens);
        carrinhoRepository.delete(carrinho);
    }

    @Override
    public ItemCarrinhoResponseDTO adicionarItem(Long carrinhoId, ItemCarrinhoRequestDTO item) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(item.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (!Boolean.TRUE.equals(produto.getAtivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não está ativo");
        }
        if (item.getQuantidade() == null || item.getQuantidade() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade inválida");
        }

        ItemCarrinho novo = new ItemCarrinho();
        novo.setCarrinho(carrinho);
        novo.setProduto(produto);
        novo.setQuantidade(item.getQuantidade());
        novo.setPrecoUnitario(produto.getPreco());
        novo.setSubtotal(produto.getPreco() * item.getQuantidade());
        novo = itemCarrinhoRepository.save(novo);

        atualizarTotalCarrinho(carrinho);
        return toItemResponse(novo);
    }

    @Override
    public ItemCarrinhoResponseDTO atualizarItem(Long itemId, ItemCarrinhoRequestDTO item) {
        ItemCarrinho existente = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        Produto produto = produtoRepository.findById(item.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (!Boolean.TRUE.equals(produto.getAtivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não está ativo");
        }
        if (item.getQuantidade() == null || item.getQuantidade() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade inválida");
        }

        existente.setProduto(produto);
        existente.setQuantidade(item.getQuantidade());
        existente.setPrecoUnitario(produto.getPreco());
        existente.setSubtotal(produto.getPreco() * item.getQuantidade());
        existente = itemCarrinhoRepository.save(existente);

        atualizarTotalCarrinho(existente.getCarrinho());
        return toItemResponse(existente);
    }

    @Override
    public void removerItem(Long itemId) {
        ItemCarrinho item = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        Carrinho carrinho = item.getCarrinho();
        itemCarrinhoRepository.delete(item);
        atualizarTotalCarrinho(carrinho);
    }

    @Override
    public List<ItemCarrinhoResponseDTO> listarItens(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado"));
        List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinhoId(carrinhoId);
        return itens.stream().map(this::toItemResponse).collect(Collectors.toList());
    }

    private void atualizarTotalCarrinho(Carrinho carrinho) {
        List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinhoId(carrinho.getId());
        double total = 0.0;
        for (ItemCarrinho item : itens) {
            total += item.getSubtotal();
        }
        carrinho.setTotal(total);
        carrinhoRepository.save(carrinho);
    }

    private CarrinhoResponseDTO toResponse(Carrinho carrinho) {
        List<ItemCarrinhoResponseDTO> itens = listarItens(carrinho.getId());
        return new CarrinhoResponseDTO(
                carrinho.getId(),
                carrinho.getCliente().getId(),
                carrinho.getTotal(),
                itens
        );
    }

    private ItemCarrinhoResponseDTO toItemResponse(ItemCarrinho item) {
        return new ItemCarrinhoResponseDTO(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
        );
    }
}