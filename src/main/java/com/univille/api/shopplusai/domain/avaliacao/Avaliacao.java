package com.univille.api.shopplusai.domain.avaliacao;

import com.univille.api.shopplusai.domain.avaliacao.dto.CreateAvaliacaoRequest;
import com.univille.api.shopplusai.domain.produto.Produto;
import com.univille.api.shopplusai.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "avaliacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private LocalDate data;

}
