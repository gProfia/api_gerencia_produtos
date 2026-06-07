package com.acme.exception.mapper;

import com.acme.dto.ErroResponse;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResteasyReactiveViolationExceptionMapper implements ExceptionMapper<ValidationException>{


    @Override
    public Response toResponse(ValidationException exception) {
        return Response
        .status(Status.BAD_REQUEST)
        .entity(new ErroResponse("Campos obrigatórios não informados"))
        .build();
    }
}
