package com.univille.api.shopplusai.domain.produto.dto;

import com.univille.api.shopplusai.domain.categoria.Categoria;
import com.univille.api.shopplusai.domain.produto.Produto;
import org.jspecify.annotations.Nullable;


public record ProdutoResponse(Long id, String nome, Categoria categoria, double preco) {
    public ProdutoResponse(Produto produto){this(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPreco());}

}

