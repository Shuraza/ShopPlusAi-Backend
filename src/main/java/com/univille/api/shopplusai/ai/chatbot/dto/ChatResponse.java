package com.univille.api.shopplusai.ai.chatbot.dto;

public record ChatResponse(
        String conversationId,
        String response
) {
    public ChatResponse(String conversationId, String response){
        this.conversationId = conversationId;
        this.response = response;
    }
}
