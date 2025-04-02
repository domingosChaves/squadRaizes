package com.domingos.triboRaizes.controller;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.exception.ProdutoNotFoundException;
import com.domingos.triboRaizes.model.Produto;
import com.domingos.triboRaizes.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private BindingResult bindingResult;

    private ProdutoDTO produtoDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição do Produto", 10.0, 5);
    }

    @Test
    public void testCreateProduto_Success() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        // Act
        ResponseEntity<Produto> response = produtoController.createProduto(produtoDTO, bindingResult);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ArgumentCaptor<Produto> produtoCaptor = ArgumentCaptor.forClass(Produto.class);
        verify(produtoService, times(1)).save(produtoCaptor.capture());
        Produto savedProduto = produtoCaptor.getValue();
        assertEquals(produtoDTO.getId(), savedProduto.getId());
        assertEquals(produtoDTO.getNome(), savedProduto.getNome());
        assertEquals(produtoDTO.getDescricao(), savedProduto.getDescricao());
        assertEquals(produtoDTO.getPreco(), savedProduto.getPreco());
        assertEquals(produtoDTO.getQuantidade(), savedProduto.getQuantidade());
    }

    @Test
    public void testCreateProduto_BindingResultHasErrors() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.emptyList()); // Esteja ciente que você não precisa retornar um produto aqui

        // Act
        ResponseEntity<Produto> response = produtoController.createProduto(produtoDTO, bindingResult);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}