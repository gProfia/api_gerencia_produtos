package com.acme.dto;

import com.acme.entity.Produto;

import lombok.Builder;

@Builder
public record ProdutoResponse(
    String nome,
    String descricao,
    Double preco,
    int estoque
) {
    public static ProdutoResponse toDTO(Produto produto){
        return ProdutoResponse.builder()
            .nome(produto.getNome())
            .descricao(produto.getDescricao())
            .preco(produto.getPreco())
            .estoque(produto.getEstoque())
            .build();
    }
}
