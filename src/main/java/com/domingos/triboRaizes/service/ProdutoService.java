package com.domingos.triboRaizes.service;

import com.domingos.triboRaizes.dao.produtoDao.dao.ProdutoDAO;
import com.domingos.triboRaizes.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoDAO produtoDAO; // O Spring instanciará automaticamente

    public void save(Produto produto) {
        produtoDAO.save(produto);
    }

    public void update(Produto produto) {
        produtoDAO.update(produto); // save() tambem poderia ser usado para atualizar porém achei melhor usar o merge, optei pelo uso por ser mais explícito
    }

    public void delete(Long id) {
        produtoDAO.delete(id);  //Deleta o item usando seu id
    }

    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(produtoDAO.findById(id)); // Envolve o resultado em um Optional para evitar NullPointerException
    }

    public List<Produto> findAll() {
        List<Produto> produtos = produtoDAO.findAll();
        return produtos != null ? produtos : Collections.emptyList(); // Retorna uma lista vazia se for null
    }
}
