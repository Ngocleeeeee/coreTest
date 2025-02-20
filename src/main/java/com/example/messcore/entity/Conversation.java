package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "propertyId", nullable = false)
    private UUID propertyId;

    @Column(name = "propertyType")
    private String propertyType;

    @Column(name = "externalConversationCode")
    private String externalConversationCode;

    @Column(name = "aiConversationCode")
    private String aiConversationCode;

    @Column(name = "conversationAiSwitch")
    private Boolean conversationAiSwitch;

    @Column(name = "bookingId")
    private UUID bookingId;

    @Column(name = "bookingCode")
    private String bookingCode;

    @Column(name = "customerId")
    private UUID customerId;

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "isClosed")
    private Boolean isClosed;

    @Column(name = "nameVi")
    private String nameVi;

    @Column(name = "lastMessageId")
    private UUID lastMessageId;

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

    @Column(name = "otaId")
    private UUID otaId;

    @Column(name = "hotelId")
    private UUID hotelId;

    @Column(name = "isClose")
    private Boolean isClose;
}
