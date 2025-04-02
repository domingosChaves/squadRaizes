package com.domingos.triboRaizes.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleProdutoNotFound() {
        // Arrange
        Long idProduto = 1L; // Note que estamos usando um Long
        ProdutoNotFoundException exception = new ProdutoNotFoundException(idProduto);

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleProdutoNotFound(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Produto não encontrado com ID: 1", response.getBody()); // A mensagem correta
    }

    @Test
    public void testHandleGeneralException() {
        // Arrange
        Exception exception = new Exception("Erro inesperado");

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleGeneralException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro inesperado: Erro inesperado", response.getBody());
    }
}