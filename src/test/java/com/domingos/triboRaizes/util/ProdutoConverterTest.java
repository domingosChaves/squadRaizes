package com.domingos.triboRaizes.util;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.model.Produto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoConverterTest {

    @Test
    public void testConvertToDTO() {
        // Arrange
        Produto produto = new Produto(1L, "Produto Teste", "Descrição do Produto", 10.0, 5);

        // Act
        ProdutoDTO produtoDTO = ProdutoConverter.convertToDTO(produto);

        // Assert
        assertEquals(produto.getId(), produtoDTO.getId());
        assertEquals(produto.getNome(), produtoDTO.getNome());
        assertEquals(produto.getDescricao(), produtoDTO.getDescricao());
        assertEquals(produto.getPreco(), produtoDTO.getPreco());
        assertEquals(produto.getQuantidade(), produtoDTO.getQuantidade());
    }

    @Test
    public void testConvertToEntity() {
        // Arrange
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição do Produto", 10.0, 5);

        // Act
        Produto produto = ProdutoConverter.convertToEntity(produtoDTO);

        // Assert
        assertEquals(produtoDTO.getId(), produto.getId());
        assertEquals(produtoDTO.getNome(), produto.getNome());
        assertEquals(produtoDTO.getDescricao(), produto.getDescricao());
        assertEquals(produtoDTO.getPreco(), produto.getPreco());
        assertEquals(produtoDTO.getQuantidade(), produto.getQuantidade());
    }

    @Test
    public void testConvertListToDTO() {
        // Arrange
        Produto produto1 = new Produto(1L, "Produto 1", "Descrição do Produto 1", 10.0, 5);
        Produto produto2 = new Produto(2L, "Produto 2", "Descrição do Produto 2", 15.0, 10);
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        // Act
        List<ProdutoDTO> produtoDTOs = ProdutoConverter.convertListToDTO(produtos);

        // Assert
        assertEquals(2, produtoDTOs.size());
        assertEquals(produto1.getId(), produtoDTOs.get(0).getId());
        assertEquals(produto1.getNome(), produtoDTOs.get(0).getNome());
        assertEquals(produto2.getId(), produtoDTOs.get(1).getId());
        assertEquals(produto2.getNome(), produtoDTOs.get(1).getNome());
    }
}