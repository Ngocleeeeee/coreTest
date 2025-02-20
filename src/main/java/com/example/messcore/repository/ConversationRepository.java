package com.example.messcore.repository;

import com.example.messcore.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findById(UUID conversationId);
}

