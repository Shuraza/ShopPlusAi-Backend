package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.ai.chatbot.dto.ChatResponse;
import com.univille.api.shopplusai.domain.avaliacao.AvaliacaoService;
import com.univille.api.shopplusai.domain.produto.ProdutoService;
import com.univille.api.shopplusai.infra.client.gemini.GeminiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ProdutoService produtoService;
    private final AvaliacaoService avaliacaoService;
    private final ChatMemoryService memoryService;
    private final ChatbotPromptBuilder promptBuilder;
    private final GeminiClient geminiClient;
    private final ChatMessageRepository repository;

    public ChatResponse chat(String pergunta){

        UUID uuid = UUID.randomUUID();
        String conversationId = uuid.toString();

        var avaliacoes = avaliacaoService.getAllAvaliacoes();
        var produtos = produtoService.getAllProdutos();

        var systemPrompt = promptBuilder.systemPrompt();

        var message = promptBuilder.contextPrompt(pergunta, getAll(), avaliacoes, produtos);


    }

    public List<ChatMessage> getAll(){
        return repository.findAll();
    }

}
