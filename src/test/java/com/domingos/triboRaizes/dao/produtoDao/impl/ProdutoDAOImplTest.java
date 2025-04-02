package com.domingos.triboRaizes.dao.produtoDao.impl;

import com.domingos.triboRaizes.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProdutoDAOImplTest {

    @InjectMocks
    private ProdutoDAOImpl produtoDAO;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Produto produto = new Produto(1L, "Produto Teste", "Descrição do Produto", 100.0, 10);

        // Chama o método de salvar
        produtoDAO.save(produto);

        // Verifica se o método persist foi chamado no entityManager
        verify(entityManager, times(1)).persist(produto);
    }

    @Test
    public void testUpdate() {
        Produto produto = new Produto(1L, "Produto Teste", "Descrição do Produto", 100.0, 10);

        // Chama o método de atualizar
        produtoDAO.update(produto);

        // Verifica se o método merge foi chamado no entityManager
        verify(entityManager, times(1)).merge(produto);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Produto produto = new Produto(id, "Produto Teste", "Descrição do Produto", 100.0, 10);

        // Simula o retorno do produto ao buscar pelo ID
        when(entityManager.find(Produto.class, id)).thenReturn(produto);

        // Chama o método de deletar
        produtoDAO.delete(id);

        // Verifica se o método remove foi chamado no entityManager
        verify(entityManager, times(1)).remove(produto);
    }

    @Test
    public void testDelete_NaoEncontrado() {
        Long id = 1L;

        // Simula que não encontrou o produto ao buscar pelo ID
        when(entityManager.find(Produto.class, id)).thenReturn(null);

        // Chama o método de deletar
        produtoDAO.delete(id);

        // Verifica que o método remove não foi chamado
        verify(entityManager, never()).remove(any(Produto.class));
    }

    @Test
    public void testFindById_Success() {
        Long id = 1L;
        Produto produto = new Produto(id, "Produto Teste", "Descrição do Produto", 100.0, 10);

        // Simula o retorno do produto ao buscar pelo ID
        when(entityManager.find(Produto.class, id)).thenReturn(produto);

        // Chama o método e verifica o resultado
        Produto result = produtoDAO.findById(id);
        assertEquals(produto, result);
    }

    @Test
    public void testFindById_NotFound() {
        Long id = 1L;

        // Simula retorno nulo ao buscar pelo ID
        when(entityManager.find(Produto.class, id)).thenReturn(null);

        // Chama o método e verifica o resultado
        Produto result = produtoDAO.findById(id);
        assertNull(result); // O resultado deve ser nulo porque o produto não foi encontrado
    }

    @Test
    public void testFindAll() {
        Produto produto1 = new Produto(1L, "Produto 1", "Descrição do Produto 1", 100.0, 10);
        Produto produto2 = new Produto(2L, "Produto 2", "Descrição do Produto 2", 200.0, 5);
        List<Produto> produtos = List.of(produto1, produto2);

        // Simula o retorno da lista de produtos
        when(entityManager.createQuery("from Produto", Produto.class)).thenReturn(mock(TypedQuery.class));
        when(entityManager.createQuery("from Produto", Produto.class).getResultList()).thenReturn(produtos);

        // Chama o método e verifica o resultado
        List<Produto> result = produtoDAO.findAll();
        assertEquals(2, result.size());
        assertEquals(produto1, result.get(0));
        assertEquals(produto2, result.get(1));
    }

    @Test
    public void testFindAll_EmptyList() {
        // Simula o retorno de uma lista vazia
        when(entityManager.createQuery("from Produto", Produto.class)).thenReturn(mock(TypedQuery.class));
        when(entityManager.createQuery("from Produto", Produto.class).getResultList()).thenReturn(Collections.emptyList());

        // Chama o método e verifica o resultado
        List<Produto> result = produtoDAO.findAll();
        assertTrue(result.isEmpty());
    }
}