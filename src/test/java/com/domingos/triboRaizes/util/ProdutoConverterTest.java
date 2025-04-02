package com.domingos.triboRaizes.util;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.model.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoConverterTest {

    @Test
    public void testConvertToDTO() {
        Produto produto = new Produto(1L, "Produto1", "Descrição do Produto1", 100.0, 10);
        ProdutoDTO produtoDTO = ProdutoConverter.convertToDTO(produto);

        assertEquals(produto.getId(), produtoDTO.getId());
        assertEquals(produto.getNome(), produtoDTO.getNome());
        assertEquals(produto.getDescricao(), produtoDTO.getDescricao());
        assertEquals(produto.getPreco(), produtoDTO.getPreco());
        assertEquals(produto.getQuantidade(), produtoDTO.getQuantidade());
    }

    @Test
    public void testConvertToEntity() {
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "Produto1", "Descrição do Produto1", 100.0, 10);
        Produto produto = ProdutoConverter.convertToEntity(produtoDTO);

        assertEquals(produtoDTO.getId(), produto.getId());
        assertEquals(produtoDTO.getNome(), produto.getNome());
        assertEquals(produtoDTO.getDescricao(), produto.getDescricao());
        assertEquals(produtoDTO.getPreco(), produto.getPreco());
        assertEquals(produtoDTO.getQuantidade(), produto.getQuantidade());
    }
}