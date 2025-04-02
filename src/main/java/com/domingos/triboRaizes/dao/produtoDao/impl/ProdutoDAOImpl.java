package com.domingos.triboRaizes.dao.produtoDao.impl;
import com.domingos.triboRaizes.dao.produtoDao.dao.ProdutoDAO;
import com.domingos.triboRaizes.dto.ProdutoDTO;
import com.domingos.triboRaizes.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ProdutoDAOImpl implements ProdutoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Produto produto) {
        entityManager.persist(produto);
    }

    @Override
    public void update(Produto produto) {
        entityManager.merge(produto);
    }

    @Override
    public void delete(Long id) {
        Produto produto = findById(id);
        if (produto != null) {
            entityManager.remove(produto);
        }
    }

    @Override
    public Produto findById(Long id) {
        return entityManager.find(Produto.class, id);
    }

    @Override
    public List<Produto> findAll() {
        return entityManager.createQuery("from Produto", Produto.class).getResultList();
    }

}
