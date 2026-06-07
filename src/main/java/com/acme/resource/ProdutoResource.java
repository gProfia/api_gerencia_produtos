package com.acme.resource;

import java.util.Optional;

import com.acme.dto.ErroResponse;
import com.acme.dto.ProdutoRequest;
import com.acme.dto.ProdutoResponse;
import com.acme.entity.Produto;
import com.acme.service.ProdutoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ProdutoResource  implements ProdutosResourceAPI{

    @Inject 
    private ProdutoService produtoService;

    @Override
    public Response listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Response buscar(Long id) {

        return produtoService.buscar(id)
            .map(p -> Response.ok(ProdutoResponse.toDTO(p)).build())
            .orElseGet(
                () ->  Response
                        .status(Status.NOT_FOUND)
                        .entity(new ErroResponse("Produto não encontrado"))
                        .build()
            );
    }

    @Override
    public Response cadastrar(ProdutoRequest produtoRequest) {        
        return Response
        .status(Status.CREATED)
        .entity(
            ProdutoResponse.toDTO(
                produtoService.cadastrar(produtoRequest.toEntity())
            )
        )
        .build();
    }

    @Override
    public Response atualizar(Long id, ProdutoRequest produtoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Response remover(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

}
