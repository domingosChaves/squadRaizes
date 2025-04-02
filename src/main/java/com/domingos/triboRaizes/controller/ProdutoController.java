package com.domingos.triboRaizes.controller;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.exception.ProdutoNotFoundException;
import com.domingos.triboRaizes.model.Produto;
import com.domingos.triboRaizes.service.ProdutoService;
import com.domingos.triboRaizes.util.ProdutoConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos") // Mapeia a URL base para esta classe
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; // Injeção de dependência da camada de serviço

    // Criação de um novo produto
    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody @Valid ProdutoDTO produtoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body((Produto) result.getAllErrors());
        }
        Produto produto = new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getQuantidade());
        produtoService.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED); // Retorna 201 Created
    }

    // Atualização de um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body((Produto) result.getAllErrors());
        }

        // Verifica se o produto existe
        Optional<Produto> produtoExistente = produtoService.findById(id);

        // Lança a exceção se o produto não existir
        produtoExistente.orElseThrow(() -> new ProdutoNotFoundException(id));

        // Define o ID do produto a ser atualizado
        Produto produto = ProdutoConverter.convertToEntity(produtoDTO);
        produtoService.update(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK); // Retorna 200 OK
    }

    // Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content
    }

    // Encontrar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoService.findById(id);
        Produto produto = produtoOptional.orElseThrow(() -> new ProdutoNotFoundException(id)); // Lança a exceção se não encontrado

        ProdutoDTO produtoDTO = ProdutoConverter.convertToDTO(produto);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK); // Retorna 200 OK
    }

    // Encontrar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAllProdutos() {
        List<ProdutoDTO> produtos = ProdutoConverter.convertListToDTO(produtoService.findAll());
        return new ResponseEntity<>(produtos, HttpStatus.OK); // Retorna 200 OK com a lista de produtos
    }
}