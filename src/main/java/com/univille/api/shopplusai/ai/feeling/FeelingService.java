package com.univille.api.shopplusai.ai.feeling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univille.api.shopplusai.ai.feeling.dto.FeelingResponse;
import com.univille.api.shopplusai.domain.avaliacao.Avaliacao;
import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoRepository;
import com.univille.api.shopplusai.domain.produto.Produto;
import com.univille.api.shopplusai.domain.produto.ProdutoRepository;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import com.univille.api.shopplusai.infra.exception.NotFoundException;
import com.univille.api.shopplusai.infra.exception.ParseJsonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeelingService {

    private final GeminiClient geminiClient;
    private final FeelingPromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ProdutoRepository produtoRepository;

    public FeelingResponse analisar(int mes, int ano){

        YearMonth periodo = YearMonth.of(ano, mes);
        LocalDate inicio = periodo.atDay(1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        var avaliacoes = avaliacaoRepository.findAllByPeriodo(inicio, fim);

        if (avaliacoes.isEmpty()){
            throw new NotFoundException("Nenhuma avaliação encontrada nesse período para análise");
        }

        var produtos = produtoRepository.findAll();

        var systemPrompt = promptBuilder.systemPrompt();
        var contextPrompt = promptBuilder.contextPrompt(avaliacoes, produtos);

        var response = geminiClient.chat(systemPrompt, contextPrompt);

        response = response
                .replace("```json", "")
                .replace("```", "")
                .trim();

        try{
            FeelingResponse result = objectMapper.readValue(response, FeelingResponse.class);

            if (
                    result.resumo() == null ||
                    result.mediaSentimento() == null ||
                    result.melhorProduto() == null ||
                    result.piorProduto() == null
            ){
                throw new ParseJsonException("IA retornou JSON incompleto");
            }

            return result;

        }catch (JsonProcessingException e){
            throw new ParseJsonException("Erro ao converter resposta da IA para JSON");
        }

    }

}
