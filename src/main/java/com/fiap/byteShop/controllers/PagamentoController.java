package com.fiap.byteShop.controllers;

import com.fiap.byteShop.dtos.PagamentoRequestDTO;
import com.fiap.byteShop.dtos.PagamentoResponseDTO;
import com.fiap.byteShop.dtos.PagamentoStatusUpdateDTO;
import com.fiap.byteShop.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos", description = "Operações para gerenciar pagamentos de pedidos")
@RestController
@RequestMapping("/byte-shop/v1/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(
            summary = "Cria um pagamento",
            description = "Realiza o pagamento de um pedido. O valor deve ser igual ao total do pedido e o método deve ser um dos aceitos (PIX, CARTAO, BOLETO)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Já existe pagamento para este pedido")
    })
    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criarPagamento(
            @RequestBody PagamentoRequestDTO request) {
        return ResponseEntity.status(201).body(pagamentoService.criarPagamento(request));
    }

    @Operation(
            summary = "Busca pagamento por ID",
            description = "Retorna os dados do pagamento pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPagamento(
            @Parameter(description = "ID do pagamento", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPagamento(id));
    }

    @Operation(
            summary = "Atualiza status do pagamento",
            description = "Atualiza o status do pagamento para PENDENTE, APROVADO ou RECUSADO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado"),
            @ApiResponse(responseCode = "400", description = "Status inválido"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<PagamentoResponseDTO> atualizarStatus(
            @Parameter(description = "ID do pagamento", example = "10") @PathVariable Long id,
            @RequestBody PagamentoStatusUpdateDTO statusUpdate) {
        return ResponseEntity.ok(pagamentoService.atualizarStatus(id, statusUpdate));
    }
}