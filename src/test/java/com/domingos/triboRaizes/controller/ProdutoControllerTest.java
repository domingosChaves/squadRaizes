package com.domingos.triboRaizes.controller;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.exception.ProdutoNotFoundException;
import com.domingos.triboRaizes.model.Produto;
import com.domingos.triboRaizes.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduto_Success() {
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Produto1", "Descrição do Produto", 100.0, 10);
        Produto produto = new Produto(1L, "Produto1", "Descrição do Produto", 100.0, 10);

        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(produtoService).save(any(Produto.class));

        ResponseEntity<Produto> response = produtoController.createProduto(produtoDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto1", response.getBody().getNome());
    }

    @Test
    public void testCreateProduto_ValidationError() {
        ProdutoDTO produtoDTO = new ProdutoDTO(0L, "", "Descrição do Produto", 100.0, 10);

        // Simula a execução com erros de validação
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new org.springframework.validation.ObjectError("nome", "O nome do produto não pode ser vazio")));

        ResponseEntity<?> response = produtoController.createProduto(produtoDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List); // Verifica se o corpo da resposta é uma lista
    }

    @Test
    public void testUpdateProduto_Success() {
        Long id = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO(id, "Produto Atualizado", "Descrição Atualizada", 150.0, 5);
        Produto produtoExistente = new Produto(id, "Produto Antigo", "Descrição Antiga", 100.0, 10);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(produtoService.findById(id)).thenReturn(Optional.of(produtoExistente));
        doNothing().when(produtoService).update(any(Produto.class));

        ResponseEntity<Produto> response = produtoController.updateProduto(id, produtoDTO, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto Atualizado", response.getBody().getNome());
    }

    @Test
    public void testUpdateProduto_NotFound() {
        Long id = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO(id, "Produto Atualizado", "Descrição Atualizada", 150.0, 5);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(produtoService.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoController.updateProduto(id, produtoDTO, bindingResult);
        });
    }

    @Test
    public void testDeleteProduto_Success() {
        Long id = 1L;

        produtoController.deleteProduto(id);

        verify(produtoService, times(1)).delete(id);
    }
}