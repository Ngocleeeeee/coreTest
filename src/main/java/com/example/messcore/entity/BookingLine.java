package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "booking_line")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class BookingLine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "bookingId")
    private UUID bookingId;

    @Column(name = "ratePlanId")
    private UUID ratePlanId;

    @Column(name = "ratePlanCode")
    private String ratePlanCode;

    @Column(name = "roomRateId")
    private UUID roomRateId;

    @Column(name = "roomTypeId")
    private UUID roomTypeId;

    @Column(name = "roomCode")
    private String roomCode;

    @Column(name = "ratePlanName")
    private String ratePlanName;

    @Column(name = "externalId")
    private UUID externalId;

    @Column(name = "bookingLineType")
    private Integer bookingLineType;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "adults")
    private Integer adults;

    @Column(name = "baby")
    private Integer baby;

    @Column(name = "children")
    private Integer children;

    @Column(name = "extraAdults")
    private Integer extraAdults;

    @Column(name = "extraBaby")
    private Integer extraBaby;

    @Column(name = "extraChildren")
    private Integer extraChildren;

    @Column(name = "amountExtraAdult")
    private BigDecimal amountExtraAdult;

    @Column(name = "amountExtraBaby")
    private BigDecimal amountExtraBaby;

    @Column(name = "amountExtraChildren")
    private BigDecimal amountExtraChildren;

    @Column(name = "amountPax")
    private BigDecimal amountPax;

    @Column(name = "guestName")
    private String guestName;

    @Column(name = "specialRequest")
    private String specialRequest;

    @Column(name = "description")
    private String description;

    @Column(name = "lineTotal")
    private BigDecimal lineTotal;

    @Column(name = "nights")
    private Integer nights;

    @Column(name = "unitPrice")
    private BigDecimal unitPrice;

    @Column(name = "profileId")
    private String profileId;

    @Column(name = "serviceRateId")
    private String serviceRateId;

    @Column(name = "ticketCodeData")
    private String ticketCodeData;

    @Column(name = "percent")
    private BigDecimal percent;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "isIncludedTax")
    private Boolean isIncludedTax;

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
