package com.acme.dto;

import com.acme.entity.Usuario;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
    @NotBlank
    String nome,
    @NotBlank
    String email,
    @NotBlank
    String senha,
    @NotBlank
    String role
) {
    public Usuario toEntity(){
        return Usuario.builder()
            .nome(nome)
            .email(email)
            .senha(BcryptUtil.bcryptHash(senha))
            .role(role)
            .build();
    }
}
