package com.example.messcore.repository;

import ezcloud.message.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Hotel findByHotelCode(String hotelCode);
}
