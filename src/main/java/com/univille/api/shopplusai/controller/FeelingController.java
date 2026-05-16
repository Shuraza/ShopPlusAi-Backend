package com.univille.api.shopplusai.controller;

import com.univille.api.shopplusai.ai.feeling.FeelingService;
import com.univille.api.shopplusai.ai.feeling.dto.FeelingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeling")
@RequiredArgsConstructor
public class FeelingController {

    private final FeelingService service;

    @GetMapping
    public ResponseEntity<FeelingResponse> analisar(@RequestParam int ano, @RequestParam int mes){
        return ResponseEntity.ok().body(service.analisar(mes, ano));
    }

}
