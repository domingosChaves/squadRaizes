package com.domingos.triboRaizes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;



@Data
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "O nome do produto não pode ser vazio")
    @Size(min = 1, max = 100, message = "O nome do produto deve ter entre 1 e 100 caracteres")
    private String nome;

    @Size(max = 255, message = "A descrição do produto deve ter no máximo 255 caracteres")
    private String descricao;

    @NotNull(message = "O preço do produto não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private Double preco;

    @NotNull(message = "A quantidade do produto não pode ser nula")
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    private Integer quantidade;

    public ProdutoDTO(Long id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
