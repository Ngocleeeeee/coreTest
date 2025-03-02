package com.example.messcore.repository;

import ezcloud.message.booking.Booking;
import ezcloud.message.booking.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Booking findBookingById(UUID uuid);
}
