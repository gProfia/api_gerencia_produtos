package com.acme.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
    @NotEmpty
    String email,
    @NotEmpty
    String senha
) {

}
