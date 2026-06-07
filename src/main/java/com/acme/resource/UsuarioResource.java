package com.acme.resource;

import com.acme.dto.ErroResponse;
import com.acme.dto.LoginRequest;
import com.acme.dto.LoginResponse;
import com.acme.dto.UsuarioRequest;
import com.acme.dto.UsuarioResponse;
import com.acme.service.UsuarioService;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class UsuarioResource implements UsuarioResourceAPI{

    @Inject
    private UsuarioService usuarioService;

    @Override
    public Response cadastrar(UsuarioRequest usuarioRequest) {
        if(usuarioService.existe(usuarioRequest.email()))
            return Response.status(Response.Status.CONFLICT) // HTTP 409
                           .entity(new ErroResponse("E-mail já cadastrado"))
                           .build();
       
        return Response.status(Status.CREATED)
        .entity(
            UsuarioResponse.toDTO(
                usuarioService.cadastrar(usuarioRequest.toEntity())))
        .build();
    }   

    @Override
    public Response login(LoginRequest loginRequest){

       return usuarioService.login(loginRequest.email(), loginRequest.senha())
        .map(optLogin ->
             Response.ok(optLogin).build())
        .orElseGet(
          () ->  Response.status(Status.UNAUTHORIZED)
                .entity(new ErroResponse("E-mail ou senha inválidos"))
                .build());
    }

}
