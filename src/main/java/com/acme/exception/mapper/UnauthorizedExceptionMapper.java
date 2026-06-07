package com.acme.exception.mapper;

import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.acme.dto.ErroResponse;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException exception) {

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErroResponse("Não autenticado"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}