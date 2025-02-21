package com.example.messcore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageWrapper {
    private MessageData data;

    @Data
    public static class MessageData {
        private String type;
        private MessageAttributes attributes;
        private Relationships relationships;
    }

    @Data
    public static class MessageAttributes {
        private String externalMessageCode;
        private Boolean fromAi;
        private String content;
        private String contentType;
        private Boolean isProperty;
        private Boolean isRead;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updatedDate;
    }

    @Data
    public static class Relationships {
        @JsonProperty("conversation")
        private RelationshipConversationData conversation;
        @JsonProperty("customer")
        private RelationshipData customer;
        @JsonProperty("staff")
        private RelationshipData staff;
        @JsonProperty("booking")
        private RelationshipData booking;
        @JsonProperty("property")
        private RelationshipsData property;
    }


    @Data
    public static class RelationshipData {
        private UUID id;
    }
    @Data
    public static class RelationshipConversationData {
        private UUID id;
        private String nameVi;
        private String externalConversationCode;
        private String aiConversationCode;
    }
    @Data
    public static class RelationshipsData {
        private UUID id;
        private String type;
    }
}
