package com.domingos.triboRaizes.controller;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.model.Produto;
import com.domingos.triboRaizes.service.ProdutoService;
import com.domingos.triboRaizes.util.ProdutoConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos") // Mapeia a URL base para esta classe
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; // Injeção de dependência da camada de serviço

    // Criação de um novo produto
    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO.getId(),produtoDTO.getNome(),produtoDTO.getDescricao(),produtoDTO.getPreco(),produtoDTO.getQuantidade());
        produtoService.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED); // Retorna 201 Created
    }

    // Atualização de um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {

                // Verifica se o produto existe
        Optional<Produto> produtoExistente = produtoService.findById(id);

        if (!produtoExistente.isPresent()) {
            // Retorna uma resposta 404 Not Found se o produto não existir
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Define o ID do produto a ser atualizado
        Produto produto = ProdutoConverter.convertToEntity(produtoDTO);
        produtoService.update(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK); // Retorna 200 OK
    }


    // Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found se o produto não existir
        }
    }

    // Encontrar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoService.findById(id);
        return produtoOptional.map(produto -> {
            ProdutoDTO produtoDTO = ProdutoConverter.convertToDTO(produto);
            return new ResponseEntity<>(produtoDTO, HttpStatus.OK); // Retorna 200 OK
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Retorna 404 Not Found
    }

    // Encontrar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAllProdutos() {
        List<ProdutoDTO> produtos = ProdutoConverter.convertListToDTO(produtoService.findAll()) ;
        return new ResponseEntity<>(produtos, HttpStatus.OK); // Retorna 200 OK com a lista de produtos
    }
}
