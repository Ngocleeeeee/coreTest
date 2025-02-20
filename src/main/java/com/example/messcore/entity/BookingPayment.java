package com.example.messcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "booking_payment")
@Data 
@AllArgsConstructor
@NoArgsConstructor public class BookingPayment {
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

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "appliedRate")
    private BigDecimal appliedRate;

    @Column(name = "batchNo")
    private String batchNo;

    @Column(name = "cardInfo")
    private String cardInfo;

    @Column(name = "currencyCode")
    private String currencyCode;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "ezpayRef")
    private String ezpayRef;

    @Column(name = "isRemind")
    private Boolean isRemind;

    @Column(name = "note")
    private String note;

    @Column(name = "paymentDeadline")
    private LocalDateTime paymentDeadline;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "paymentStatus")
    private Integer paymentStatus;

    @Column(name = "paymentType")
    private Integer paymentType;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "receiptNo")
    private String receiptNo;

    @Column(name = "remind")
    private Byte remind;

    @Column(name = "responseCode")
    private String responseCode;

    @Column(name = "responseMessage")
    private String responseMessage;

    @Column(name = "times")
    private Byte times;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    @Column(name = "transactionNo")
    private String transactionNo;

    @Column(name = "transactionRef")
    private String transactionRef;

    @Column(name = "bookingId")
    private UUID bookingId;

    @Column(name = "pmsRef")
    private String pmsRef;
}
