package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "conversationId")
    private UUID conversationId;

    @Column(name = "customerId")
    private UUID customerId;

    @Column(name = "staffId")
    private UUID staffId;

    @Column(name = "externalMessageCode")
    private String externalMessageCode;

    @Column(name = "fromAi")
    private Boolean fromAi;

    @Column(name = "content")
    private String content;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "isProperty")
    private Boolean isProperty;

    @Column(name = "isRead")
    private Boolean isRead;

    @Column(name = "version")
    private Long version;

    @Column(name = "active")
    private Byte active;

    @Column(name = "createdBy")
    private UUID createdBy;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "sortIndex")
    private Integer sortIndex;

    @Column(name = "updatedBy")
    private UUID updatedBy;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
}
