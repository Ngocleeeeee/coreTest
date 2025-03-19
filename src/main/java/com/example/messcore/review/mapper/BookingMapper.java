package com.example.messcore.review.mapper;

import com.example.messcore.messsage.dto.BookingDto;
import ezcloud.message.booking.Booking;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(BookingDto bookingDto);

    @AfterMapping
    default void linkBookingPayments(@MappingTarget Booking booking) {
        booking.getBookingPayments().forEach(bookingPayment -> bookingPayment.setBooking(booking));
    }

    BookingDto toDto(Booking booking);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Booking partialUpdate(BookingDto bookingDto, @MappingTarget Booking booking);
}