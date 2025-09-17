package com.fiap.byteShop.controllers;

import com.fiap.byteShop.dtos.ClienteRequestDTO;
import com.fiap.byteShop.dtos.ClienteResponseDTO;
import com.fiap.byteShop.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Clientes", description = "Operações para gerenciar clientes")
@RestController
@RequestMapping("/byte-shop/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Lista todos os clientes",
            description = "Retorna todos os clientes cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @Operation(
            summary = "Busca cliente por ID",
            description = "Retorna os dados do cliente conforme o ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @Parameter(description = "ID do cliente", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @Operation(
            summary = "Cria um novo cliente",
            description = "Cadastra um novo cliente. O e-mail deve ser único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado"),
            @ApiResponse(responseCode = "400", description = "Dados obrigatórios ausentes"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(201).body(clienteService.save(dto));
    }

    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza os dados do cliente informado pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados obrigatórios ausentes"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @Parameter(description = "ID do cliente", example = "10") @PathVariable Long id,
            @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.update(id, dto));
    }

    @Operation(
            summary = "Exclui um cliente",
            description = "Remove o cliente do sistema, desde que não possua pedidos vinculados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "409", description = "Cliente possui pedidos vinculados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do cliente", example = "10") @PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}