package com.univille.api.shopplusai.ai.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {

    private final ChatMessageRepository repository;

    public void saveUserMessage(
            String conversationId,
            String mensagem
    ){

        ChatMessage message = new ChatMessage();

        message.setConversationId(conversationId);
        message.setRole(MessageRole.USER);
        message.setContent(mensagem);
        message.setCreatedAt(LocalDateTime.now());

        repository.save(message);
    }

    public void saveAssistantMessage(
            String conversationId,
            String mensagem
    ){

        ChatMessage message = new ChatMessage();

        message.setConversationId(conversationId);
        message.setRole(MessageRole.ASSISTANT);
        message.setContent(mensagem);
        message.setCreatedAt(LocalDateTime.now());

        repository.save(message);
    }
}
