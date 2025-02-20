package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotel_review")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class HotelReview {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "hotelId")
    private UUID hotelId;

    @Column(name = "otaId")
    private UUID otaId;

    @Column(name = "otaCode")
    private String otaCode;

    @Column(name = "overallScore")
    private Double overallScore;

    @Column(name = "overallCount")
    private Integer overallCount;

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
