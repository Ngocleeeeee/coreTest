package com.example.messcore.messsage.repository;

import ezcloud.message.messenger.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findById(UUID conversationId);
    Conversation findConversationById(UUID conversationID);
    Optional<Conversation> findByCustomerIdAndPropertyId(UUID customerId,UUID propertyId);
}

