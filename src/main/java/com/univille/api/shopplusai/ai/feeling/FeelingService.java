package com.univille.api.shopplusai.ai.feeling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univille.api.shopplusai.ai.feeling.dto.FeelingResponse;
import com.univille.api.shopplusai.domain.avaliacao.Avaliacao;
import com.univille.api.shopplusai.domain.produto.Produto;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import com.univille.api.shopplusai.infra.exception.ParseJsonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeelingService {

    private final GeminiClient geminiClient;
    private final FeelingPromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;

    public FeelingResponse analisar(List<Avaliacao> avaliacoes, List<Produto> produtos){

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

        }catch (Exception e){
            throw new ParseJsonException("Erro ao converter resposta da IA para JSON");
        }

    }

}
