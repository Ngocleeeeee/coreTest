package com.example.messcore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String externalMessageCode;
    private Boolean fromAi;
    private String content;
    private String contentType;
    private Boolean isProperty;
    private Boolean isRead;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;
    private Relationship relationship;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Relationship {
        private RelationData conversation;
        private RelationData customer;
        private RelationData staff;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelationData {
        private UUID id;
        private String type;
    }
}
