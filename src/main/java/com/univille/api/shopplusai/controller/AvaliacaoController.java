package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoService;
import com.univille.api.shopplusai.domain.avaliacao.dto.AvaliacaoResponse;
import com.univille.api.shopplusai.domain.avaliacao.dto.CreateAvaliacaoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> save(@RequestBody @Valid CreateAvaliacaoRequest dados, UriComponentsBuilder uriBuilder) {
        var avaliacao = service.save(dados);
        var uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(avaliacao.id()).toUri();
        return ResponseEntity.created(uri).body(avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> getById(@PathVariable Long id) {
        var avaliacao = service.getById(id);
        return ResponseEntity.ok(avaliacao);
    }

    @GetMapping
    public ResponseEntity<Page<AvaliacaoResponse>> getAll(@PageableDefault Pageable paginacao) {
        var page = service.getAll(paginacao);
        return ResponseEntity.ok(page);
    }
}