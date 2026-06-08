package com.acme.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.acme.dto.ErroResponse;
import com.acme.dto.ProdutoRequest;
import com.acme.dto.ProdutoResponse;
import com.acme.service.ProdutoService;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ProdutoResource  implements ProdutosResourceAPI{

    @Inject 
    private ProdutoService produtoService;

    @Inject
    JsonWebToken jwt;

    @Override
    @CacheResult(cacheName = "produtos")
    public Response listar() {
        return Response.ok(
            produtoService.listar()
            .stream()
            .map(p -> ProdutoResponse.toDTO(p))
            .toList()
        ).build();
    }

    @Override
    @CacheResult(cacheName = "produtos")
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
    @CacheInvalidateAll(cacheName = "produtos")
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
    @CacheInvalidateAll(cacheName = "produtos")
    public Response atualizar(Long id, ProdutoRequest produtoRequest) {
        return produtoService.atualizar(id, produtoRequest.toEntity())
            .map(p -> Response.ok(ProdutoResponse.toDTO(p)).build())
            .orElseGet(
                () ->  Response
                        .status(Status.NOT_FOUND)
                        .entity(new ErroResponse("Produto não encontrado"))
                        .build()
            );
    }

    @Override
    @CacheInvalidateAll(cacheName = "produtos")
    public Response remover(Long id) {
        if (produtoService.remover(id)){
            return Response.noContent().build();
        }else{
           return Response
                    .status(Status.NOT_FOUND)
                    .entity(new ErroResponse("Produto não encontrado"))
                    .build();
        }
    }

}
