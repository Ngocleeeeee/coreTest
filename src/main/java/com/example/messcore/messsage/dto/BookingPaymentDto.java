package com.example.messcore.messsage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link ezcloud.message.booking.BookingPayment}
 */
@Getter
@Setter
public class BookingPaymentDto implements Serializable {

    private byte active;
    private String createdDate;
    private UUID createdBy;
    private String updatedDate;
    private UUID updatedBy;
    private BigDecimal amount;
    private int paymentType;
    private int paymentStatus;
    private String responseCode;
    private String responseMessage;
    private String transactionDate;
    private String currencyCode;
    private BigDecimal appliedRate;
    private String note;
    private String paymentDeadline;
}