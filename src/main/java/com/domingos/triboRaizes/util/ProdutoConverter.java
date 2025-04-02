// ProdutoConverter.java
package com.domingos.triboRaizes.util;

import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoConverter {

    public static ProdutoDTO convertToDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
    }

    public static Produto convertToEntity(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getQuantidade());
    }

    public static List<ProdutoDTO> convertListToDTO(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getQuantidade()
                ))
                .collect(Collectors.toList());
    }
}