package com.domingos.triboRaizes.dao.produtoDao.dao;

import com.domingos.triboRaizes.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoDAO {
    void save(Produto produto);

    void update(Produto produto);

    void delete(Long id);

    Produto findById(Long id);

    List<Produto> findAll();
}
