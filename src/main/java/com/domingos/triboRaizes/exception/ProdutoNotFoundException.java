package com.domingos.triboRaizes.exception;

public class ProdutoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException(Long id) {
        super("Produto não encontrado com ID: " + id);
    }
}