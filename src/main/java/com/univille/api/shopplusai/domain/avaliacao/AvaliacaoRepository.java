package com.univille.api.shopplusai.domain.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.data BETWEEN :inicio AND :fim")
    List<Avaliacao> findAllByPeriodo(LocalDate inicio, LocalDate fim);
}
