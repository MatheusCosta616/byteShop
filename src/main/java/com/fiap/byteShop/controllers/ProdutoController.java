package com.fiap.byteShop.controllers;

import com.fiap.byteShop.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("byte-shop/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.save(produtoDTO);
        return ResponseEntity.status(201).body(produtoDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllProdutos(){
        return ResponseEntity.ok(produtoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        produtoService.update(id, produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
