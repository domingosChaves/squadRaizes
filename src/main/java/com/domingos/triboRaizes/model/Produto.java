package com.domingos.triboRaizes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produtos") // Nome da tabela no banco de dados
@Data
public class Produto {

    @Id // Anotação correta do JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id; // Identificador da entidade
    private String nome; // Nome do produto
    private String descricao; // Descrição do produto
    private double preco; // Preço do produto
    private int quantidade; // Quantidade do produto
}