package com.acme.dto;

import com.acme.entity.Usuario;
import lombok.Builder;

@Builder
public record UsuarioResponse(  
    
    Long id,
    String nome,
    String email,
    String role)
{
    public static UsuarioResponse toDTO(Usuario usuario){
        return UsuarioResponse.builder()
            .id(usuario.getId())
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .role(usuario.getRole())
            .build();
    }
}
