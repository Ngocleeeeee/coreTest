package com.example.messcore.messsage.dto;

import com.example.messcore.hotel.dto.HotelI18nDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

/**
 * DTO for {@link ezcloud.message.booking.Booking}
 */
@Getter
@Setter
public class BookingDto implements Serializable {

    private UUID id;
    private byte active;
    private String createdDate;
    private String updatedDate;
    private String propertyExternalCode;
    private String propertyCode;
    private String propertyType;
    private String externalBookingId;
    private String bookingCode;
    private String bookingLinkCode;
    private String otaBookingCode;
    private OTADto ota;
    private String otaCode;
    private String arrival;
    private String departure;
    private Integer bitUnmap;
    private String comment;
    private String languageCode;
    private String currencyCode;
    private BigDecimal currencyRate;
    private String extra;
    private int bookingStatus;
    private String bookingName;
    private String bookingAccount;
    private short adults;
    private short children;
    private short baby;
    private BigDecimal amount;
    private BigDecimal amountTax;
    private String promoCode;
    private String cancellationBy;
    private String cancellationReason;
    private int cancellationType;
    private String cancellationRules;
    private String paymentRules;
    private String bookingChannel;
    private boolean isEarlyCheckin;
    private boolean isHighFloor;
    private boolean isNoSmokingRoom;
    private int nights;
    private String otherNotes;
    private boolean isOtherRequests;
    private BigDecimal usedServiceFee;
    private BigDecimal usedVAT;
    private String username;
    private BigDecimal payableAmount;
    private BigDecimal subTotal;
    private String bookingMethod;
    private String gatewayCode;
    private String hotelPhone;
    private String hotelEmail;
    private Collection<BookingPaymentDto> bookingPayments;
    private Collection<HotelI18nDto> hotelI18ns;
    private CustomerDto customer;
}