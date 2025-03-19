package com.example.messcore.review.repository;

import ezcloud.message.booking.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingPaymentRepository extends JpaRepository<BookingPayment, UUID> {
}