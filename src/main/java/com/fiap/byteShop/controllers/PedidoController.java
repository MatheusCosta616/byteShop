package com.fiap.byteShop.controllers;

import com.fiap.byteShop.dtos.PedidoCreateRequestDTO;
import com.fiap.byteShop.dtos.PedidoResponseDTO;
import com.fiap.byteShop.dtos.ItemPedidoResponseDTO;
import com.fiap.byteShop.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/byte-shop/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoCreateRequestDTO request) {
        return ResponseEntity.status(201).body(pedidoService.criarPedido(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(@RequestParam(required = false) Long clienteId,
                                                                 @RequestParam(required = false) String status) {
        return ResponseEntity.ok(pedidoService.listarPedidos(clienteId, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarItens(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarItensPedido(id));
    }
}
