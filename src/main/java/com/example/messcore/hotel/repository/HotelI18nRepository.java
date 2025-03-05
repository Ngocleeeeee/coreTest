package com.example.messcore.hotel.repository;


import ezcloud.message.hotel.Hotel;
import ezcloud.message.hotel.HotelI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelI18nRepository extends JpaRepository<HotelI18n, UUID> {
}