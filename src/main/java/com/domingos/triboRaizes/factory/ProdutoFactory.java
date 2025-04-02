package com.domingos.triboRaizes.factory;

import com.domingos.triboRaizes.dao.produtoDao.dao.ProdutoDAO;
import com.domingos.triboRaizes.dao.produtoDao.impl.ProdutoDAOImpl;

public class ProdutoFactory {
    public static ProdutoDAO createProdutoDAO() {
        return new ProdutoDAOImpl();
    }
}
