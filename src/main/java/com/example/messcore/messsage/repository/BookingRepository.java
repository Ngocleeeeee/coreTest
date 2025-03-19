package com.example.messcore.messsage.repository;

import ezcloud.message.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findBookingByExternalBookingId(String externalBookingId);

}
