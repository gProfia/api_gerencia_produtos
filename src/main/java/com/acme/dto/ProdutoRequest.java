package com.acme.dto;

import com.acme.entity.Produto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequest(
    @NotEmpty
    String nome,
    @NotEmpty
    String descricao,
    @NotNull
    Double preco,
    @NotNull
    Integer estoque
) {
    public Produto toEntity(){
        return Produto.builder()
            .nome(nome)
            .descricao(descricao)
            .preco(preco)
            .estoque(estoque)
            .build();
    }
}
