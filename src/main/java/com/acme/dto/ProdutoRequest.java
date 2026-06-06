package com.acme.dto;

import com.acme.entity.Produto;

public record ProdutoRequest(
    String nome,
    String descricao,
    Double preco,
    int estoque
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
