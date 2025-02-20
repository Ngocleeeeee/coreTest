package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class Comment {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "reviewId")
    private UUID reviewId;

    @Column(name = "customerId")
    private UUID customerId;

    @Column(name = "staffId")
    private UUID staffId;

    @Column(name = "externalCommentCode")
    private String externalCommentCode;

    @Column(name = "content")
    private String content;

    @Column(name = "isProperty")
    private Boolean isProperty;

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
