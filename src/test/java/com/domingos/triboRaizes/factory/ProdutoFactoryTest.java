package com.domingos.triboRaizes.factory;

import com.domingos.triboRaizes.dao.produtoDao.dao.ProdutoDAO;
import com.domingos.triboRaizes.dao.produtoDao.impl.ProdutoDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProdutoFactoryTest {

    @Test
    public void testCreateProdutoDAO() {
        // Act
        ProdutoDAO produtoDAO = ProdutoFactory.createProdutoDAO();

        // Assert
        assertTrue(produtoDAO instanceof ProdutoDAOImpl, "Deveria retornar uma instância de ProdutoDAOImpl");
    }
}