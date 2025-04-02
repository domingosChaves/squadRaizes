package com.domingos.triboRaizes.service;

import com.domingos.triboRaizes.dao.produtoDao.dao.ProdutoDAO;
import com.domingos.triboRaizes.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoDAO produtoDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Produto produto = new Produto(1L, "Produto1", "Descrição do Produto", 100.0, 10);

        // Chama o método de salvar
        produtoService.save(produto);

        // Verifica se o método save foi chamado no produtoDAO
        verify(produtoDAO, times(1)).save(produto);
    }

    @Test
    public void testUpdate() {
        Produto produto = new Produto(1L, "Produto1", "Descrição do Produto", 100.0, 10);

        // Chama o método de atualizar
        produtoService.update(produto);

        // Verifica se o método update foi chamado no produtoDAO
        verify(produtoDAO, times(1)).update(produto);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        // Chama o método de deletar
        produtoService.delete(id);

        // Verifica se o método delete foi chamado no produtoDAO
        verify(produtoDAO, times(1)).delete(id);
    }

    @Test
    public void testFindById_Success() {
        Long id = 1L;
        Produto produto = new Produto(id, "Produto1", "Descrição do Produto", 100.0, 10);

        // Simula o retorno do produto ao buscar pelo ID
        when(produtoDAO.findById(id)).thenReturn(produto);

        // Chama o método e verifica o resultado
        Optional<Produto> result = produtoService.findById(id);
        assertTrue(result.isPresent());
        assertEquals(produto, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        Long id = 1L;

        // Simula retorno nulo ao buscar pelo ID
        when(produtoDAO.findById(id)).thenReturn(null);

        // Chama o método e verifica o resultado
        Optional<Produto> result = produtoService.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindAll() {
        Produto produto1 = new Produto(1L, "Produto1", "Descrição do Produto", 100.0, 10);
        Produto produto2 = new Produto(2L, "Produto2", "Descrição do Produto", 200.0, 5);
        List<Produto> produtos = List.of(produto1, produto2);

        // Simula o retorno da lista de produtos
        when(produtoDAO.findAll()).thenReturn(produtos);

        // Chama o método e verifica o resultado
        List<Produto> result = produtoService.findAll();
        assertEquals(2, result.size());
        assertEquals(produto1, result.get(0));
        assertEquals(produto2, result.get(1));
    }

    @Test
    public void testFindAll_EmptyList() {
        // Simula o retorno de uma lista vazia
        when(produtoDAO.findAll()).thenReturn(Collections.emptyList());

        // Chama o método e verifica o resultado
        List<Produto> result = produtoService.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAll_NullList() {
        // Simula o retorno nulo
        when(produtoDAO.findAll()).thenReturn(null);

        // Chama o método e verifica o resultado
        List<Produto> result = produtoService.findAll();
        assertTrue(result.isEmpty()); // Deve retornar lista vazia
    }
}