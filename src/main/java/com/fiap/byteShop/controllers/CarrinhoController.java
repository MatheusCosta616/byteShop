package com.fiap.byteShop.controllers;

import com.fiap.byteShop.dtos.CarrinhoCreateRequestDTO;
import com.fiap.byteShop.dtos.CarrinhoResponseDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoRequestDTO;
import com.fiap.byteShop.dtos.ItemCarrinhoResponseDTO;
import com.fiap.byteShop.services.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carrinhos", description = "Operações relacionadas a carrinhos e itens do carrinho")
@RestController
@RequestMapping("/byte-shop/v1/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Operation(
            summary = "Cria um novo carrinho para o cliente",
            description = "Cria um carrinho vinculado ao cliente informado. Um cliente só pode ter 1 carrinho ativo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Cliente já possui carrinho ativo")
    })
    @PostMapping
    public ResponseEntity<CarrinhoResponseDTO> criarCarrinho(
            @RequestBody CarrinhoCreateRequestDTO request) {
        CarrinhoResponseDTO response = carrinhoService.criarCarrinho(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Busca carrinho pelo ID",
            description = "Retorna o carrinho e seus itens pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado"),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoResponseDTO> buscarCarrinho(
            @Parameter(description = "ID do carrinho", example = "1")
            @PathVariable Long id) {
        CarrinhoResponseDTO response = carrinhoService.buscarCarrinho(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Deleta carrinho pelo ID",
            description = "Remove o carrinho e todos os seus itens."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrinho deletado"),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarrinho(
            @Parameter(description = "ID do carrinho", example = "1")
            @PathVariable Long id) {
        carrinhoService.deletarCarrinho(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Adiciona item ao carrinho",
            description = "Adiciona um produto ao carrinho informado. O produto deve estar ativo e a quantidade deve ser >= 1."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item adicionado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Carrinho ou produto não encontrado")
    })
    @PostMapping("/{id}/itens")
    public ResponseEntity<ItemCarrinhoResponseDTO> adicionarItem(
            @Parameter(description = "ID do carrinho", example = "1")
            @PathVariable Long id,
            @RequestBody ItemCarrinhoRequestDTO request) {
        ItemCarrinhoResponseDTO response = carrinhoService.adicionarItem(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Atualiza item do carrinho",
            description = "Atualiza a quantidade de um item no carrinho. O produto deve estar ativo e a quantidade >= 1."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Item ou produto não encontrado")
    })
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<ItemCarrinhoResponseDTO> atualizarItem(
            @Parameter(description = "ID do item do carrinho", example = "10")
            @PathVariable Long itemId,
            @RequestBody ItemCarrinhoRequestDTO request) {
        ItemCarrinhoResponseDTO response = carrinhoService.atualizarItem(itemId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Remove item do carrinho",
            description = "Remove o item especificado do carrinho."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removido"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(
            @Parameter(description = "ID do item do carrinho", example = "10")
            @PathVariable Long itemId) {
        carrinhoService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Lista itens do carrinho",
            description = "Retorna todos os itens do carrinho informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens retornados"),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemCarrinhoResponseDTO>> listarItens(
            @Parameter(description = "ID do carrinho", example = "1")
            @PathVariable Long id) {
        List<ItemCarrinhoResponseDTO> itens = carrinhoService.listarItens(id);
        return ResponseEntity.ok(itens);
    }
}