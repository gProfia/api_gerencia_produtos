package com.acme.resource;

import com.acme.dto.ProdutoRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProdutosResourceAPI {

    @GET
    Response listar();

    @GET
    @Path("/{id}")
    Response buscar(@PathParam("id") Long id);

    @POST
    Response cadastrar(
        @Valid @NotNull 
        ProdutoRequest produtoRequest);

    @PUT
    @Path("/{id}")
    Response atualizar(@PathParam("id") Long id, ProdutoRequest produtoRequest);

    @DELETE
    @Path("/{id}")
    Response remover(@PathParam("id") Long id);

}
