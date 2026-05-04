package com.univille.api.shopplusai.domain.avaliacao.dto;

import com.univille.api.shopplusai.domain.avaliacao.Avaliacao;
import com.univille.api.shopplusai.domain.produto.dto.ProdutoResponse;
import com.univille.api.shopplusai.domain.usuario.dto.UsuarioResponse;

import java.time.LocalDate;

public record AvaliacaoResponse(
        Long id,
        UsuarioResponse usuario,
        ProdutoResponse produto,
        String comentario,
        LocalDate data
) {
    public AvaliacaoResponse(Avaliacao avaliacao){
        this(avaliacao.getId(), new UsuarioResponse(avaliacao.getUsuario()), new ProdutoResponse(avaliacao.getProduto()), avaliacao.getComentario(), avaliacao.getData());
    }
}
