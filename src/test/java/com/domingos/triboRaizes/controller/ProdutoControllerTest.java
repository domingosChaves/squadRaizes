package com.domingos.triboRaizes.controller;


import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.exception.ProdutoNotFoundException;
import com.domingos.triboRaizes.model.Produto;
import com.domingos.triboRaizes.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ProdutoController produtoController;

    private Produto produto;
    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Produto Teste", "Descrição", 10.0, 5);
        produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição", 10.0, 5);
    }

    @Test
    void testCreateProduto_Sucesso() {
        when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<Produto> response = produtoController.createProduto(produtoDTO, bindingResult);
        assertEquals(CREATED, response.getStatusCode());
    }


    @Test
    void testFindProdutoById_Sucesso() {
        when(produtoService.findById(1L)).thenReturn(Optional.of(produto));
        ResponseEntity<ProdutoDTO> response = produtoController.findProdutoById(1L);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testFindProdutoById_NaoEncontrado() {
        when(produtoService.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundException.class, () -> produtoController.findProdutoById(1L));
    }

    @Test
    void testFindAllProdutos() {
        when(produtoService.findAll()).thenReturn(Arrays.asList(produto));
        ResponseEntity<List<ProdutoDTO>> response = produtoController.findAllProdutos();
        assertEquals(OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testUpdateProduto_Sucesso() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(produtoService.findById(1L)).thenReturn(Optional.of(produto));
        ResponseEntity<Produto> response = produtoController.updateProduto(1L, produtoDTO, bindingResult);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testUpdateProduto_NaoEncontrado() {
        when(produtoService.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundException.class, () -> produtoController.updateProduto(1L, produtoDTO, bindingResult));
    }

    @Test
    void testDeleteProduto() {
        doNothing().when(produtoService).delete(1L);
        ResponseEntity<Void> response = produtoController.deleteProduto(1L);
        assertEquals(NO_CONTENT, response.getStatusCode());
    }
}
