package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "booking")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "propertyId")
    private UUID propertyId;

    @Column(name = "propertyExternalCode")
    private String propertyExternalCode;

    @Column(name = "propertyCode")
    private String propertyCode;

    @Column(name = "propertyType")
    private String propertyType;

    @Column(name = "otaId")
    private UUID otaId;

    @Column(name = "otaCode")
    private String otaCode;

    @Column(name = "bookingCode")
    private String bookingCode;

    @Column(name = "bookingLinkCode")
    private String bookingLinkCode;

    @Column(name = "otaBookingCode")
    private String otaBookingCode;

    @Column(name = "arrival")
    private LocalDateTime arrival;

    @Column(name = "departure")
    private LocalDateTime departure;

    @Column(name = "customerId")
    private UUID customerId;

    @Column(name = "bitUnmap")
    private Integer bitUnmap;

    @Column(name = "comment")
    private String comment;

    @Column(name = "languageCode")
    private String languageCode;

    @Column(name = "currencyCode")
    private String currencyCode;

    @Column(name = "currencyRate")
    private BigDecimal currencyRate;

    @Column(name = "extra")
    private String extra;

    @Column(name = "bookingName")
    private String bookingName;

    @Column(name = "bookingStatus")
    private Integer bookingStatus;

    @Column(name = "bookingAccount")
    private String bookingAccount;

    @Column(name = "checkinStatus")
    private Integer checkinStatus;

    @Column(name = "adults")
    private Integer adults;

    @Column(name = "children")
    private Integer children;

    @Column(name = "baby")
    private Integer baby;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "amountTax")
    private BigDecimal amountTax;

    @Column(name = "promoCode")
    private String promoCode;

    @Column(name = "cancellationBy")
    private String cancellationBy;

    @Column(name = "cancellationReason")
    private String cancellationReason;

    @Column(name = "cancellationRules")
    private String cancellationRules;

    @Column(name = "cancellationType")
    private Integer cancellationType;

    @Column(name = "paymentRules")
    private String paymentRules;

    @Column(name = "bookingChannel")
    private String bookingChannel;

    @Column(name = "isEarlyCheckin")
    private Boolean isEarlyCheckin;

    @Column(name = "isHighFloor")
    private Boolean isHighFloor;

    @Column(name = "isNoSmokingRoom")
    private Boolean isNoSmokingRoom;

    @Column(name = "isOtherRequests")
    private Boolean isOtherRequests;

    @Column(name = "nights")
    private Integer nights;

    @Column(name = "otherNotes")
    private String otherNotes;

    @Column(name = "usedServiceFee")
    private BigDecimal usedServiceFee;

    @Column(name = "usedVAT")
    private BigDecimal usedVat;

    @Column(name = "username")
    private String username;

    @Column(name = "payableAmount")
    private BigDecimal payableAmount;

    @Column(name = "bookingMethod")
    private String bookingMethod;

    @Column(name = "subTotal")
    private BigDecimal subTotal;

    @Column(name = "appliedGiftCodeId")
    private UUID appliedGiftCodeId;

    @Column(name = "paymentData")
    private String paymentData;

    @Column(name = "commission")
    private BigDecimal commission;

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

    @Column(name = "externalBookingId")
    private String externalBookingId;

    @Column(name = "gatewayCode")
    private String gatewayCode;

    @Column(name = "hotelCode")
    private String hotelCode;

    @Column(name = "hotelExternalCode")
    private String hotelExternalCode;

    @Column(name = "hotelId")
    private UUID hotelId;
}
