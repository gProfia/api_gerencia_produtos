package com.acme.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import com.acme.dto.LoginResponse;
import com.acme.entity.Usuario;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

    public Boolean existe(String email){
        return !Usuario.find("email = ?1", email)
            .singleResultOptional()
            .isEmpty();
    }

    @Transactional
    public Usuario cadastrar(Usuario entity) {
        entity.persistAndFlush();
        return entity;
    }

    public Optional<LoginResponse> login(String email, String senha){
        Optional<Usuario> optUsuario = Usuario.find("email = ?1", email)
            .firstResultOptional();

        if(optUsuario.isEmpty() || !BcryptUtil.matches(senha, optUsuario.get().getSenha()))
            return Optional.empty();

        String token = Jwt.issuer("https://loja.com/issuer")
            .upn(email)
            .groups(new HashSet<>(Arrays.asList(optUsuario.get().getRole().split(","))))   
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return Optional.of(new LoginResponse(token, "Bearer", optUsuario.get().getRole()));
    }
}
