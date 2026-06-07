package com.acme.dto;

import com.acme.entity.Produto;

import lombok.Builder;

@Builder
public record ProdutoResponse(
    Long id, 
    String nome,
    String descricao,
    Double preco,
    int estoque
) {
    public static ProdutoResponse toDTO(Produto produto){
        return ProdutoResponse.builder()
            .id(produto.getId())
            .nome(produto.getNome())
            .descricao(produto.getDescricao())
            .preco(produto.getPreco())
            .estoque(produto.getEstoque())
            .build();
    }
}
