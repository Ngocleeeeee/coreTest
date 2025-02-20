package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "booking_rate")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class BookingRate {
    @Id
    @Column(name = "id")
    private UUID id;

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

    @Column(name = "appliedDate")
    private LocalDate appliedDate;

    @Column(name = "priceExtraAdult")
    private BigDecimal priceExtraAdult;

    @Column(name = "priceExtraBaby")
    private BigDecimal priceExtraBaby;

    @Column(name = "priceExtraChildren")
    private BigDecimal priceExtraChildren;

    @Column(name = "pricePax")
    private BigDecimal pricePax;

    @Column(name = "bookingLineId")
    private UUID bookingLineId;
}
