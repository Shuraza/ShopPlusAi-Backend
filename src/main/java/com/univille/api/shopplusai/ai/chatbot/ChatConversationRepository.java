package com.univille.api.shopplusai.ai.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatConversationRepository extends JpaRepository<ChatConversation, UUID> {
}
