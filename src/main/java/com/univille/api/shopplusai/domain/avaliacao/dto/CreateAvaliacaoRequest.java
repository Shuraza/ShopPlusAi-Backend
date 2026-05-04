package com.univille.api.shopplusai.domain.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAvaliacaoRequest(

        @NotNull
        Long idUsuario,

        @NotNull
        Long idProduto,

        @NotBlank
        String comentario

) {
}
