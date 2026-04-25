package com.univille.api.shopplusai.domain.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoriaRequest(
        @NotBlank
        String nome
) {
}
