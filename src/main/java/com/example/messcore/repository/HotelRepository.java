package com.example.messcore.repository;

import ezcloud.message.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Hotel findByHotelCode(String hotelCode);

    @Query("SELECT h FROM Hotel h WHERE h.active <> :deleted")
    List<Hotel> findAllByActiveNot(Byte deleted);
}
