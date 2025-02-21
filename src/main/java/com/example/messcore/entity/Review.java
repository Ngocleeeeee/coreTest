package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "review")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "propertyId")
    private UUID propertyId;

    @Column(name = "propertyType")
    private String propertyType;

    @Column(name = "bookingId")
    private UUID bookingId;

    @Column(name = "bookingCode")
    private String bookingCode;

    @Column(name = "customerId")
    private UUID customerId;

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "externalReviewCode")
    private String externalReviewCode;

    @Column(name = "isHidden")
    private Boolean isHidden;

    @Column(name = "isExpired")
    private Boolean isExpired;

    @Column(name = "title")
    private String title;

    @Column(name = "overallScore")
    private Double overallScore;

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
