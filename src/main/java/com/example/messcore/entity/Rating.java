package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "rating")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "reviewId")
    private UUID reviewId;

    @Column(name = "externalReviewCode")
    private String externalReviewCode;

    @Column(name = "title")
    private String title;

    @Column(name = "score")
    private Double score;

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
