package com.domingos.triboRaizes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Produto {

    @Id // Anotação correta do JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id; // Identificador da entidade
    private String nome; // Nome do produto
    private String descricao; // Descrição do produto
    private double preco; // Preço do produto
    private int quantidade; // Quantidade do produto

    public Produto(){}

    public Produto(Long id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}