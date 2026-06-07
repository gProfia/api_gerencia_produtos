package com.acme.resource;

import com.acme.dto.LoginRequest;
import com.acme.dto.UsuarioRequest;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UsuarioResourceAPI {

    @POST
    @Path("/register")
    @Transactional
    Response cadastrar(
        @Valid @NotNull
        UsuarioRequest usuarioRequest
    );

    @POST
    @Path("/login")
    @Transactional
    Response login(
        @Valid @NotNull
        LoginRequest loginRequest
    );
}
