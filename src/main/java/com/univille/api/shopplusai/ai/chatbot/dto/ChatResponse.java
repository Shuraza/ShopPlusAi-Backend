package com.univille.api.shopplusai.ai.chatbot.dto;

import java.util.UUID;

public record ChatResponse(
        String conversationId,
        String response
) {
    public ChatResponse(UUID conversationId, String response){
        this(conversationId.toString(), response);
    }
}
