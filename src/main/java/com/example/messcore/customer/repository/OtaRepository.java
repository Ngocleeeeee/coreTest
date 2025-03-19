package com.example.messcore.customer.repository;

import ezcloud.message.ota.OTA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OtaRepository extends JpaRepository<OTA, UUID> {
    OTA findByOtaCode(String otaCode);
    @Query("select distinct o from Booking b join b.ota o where b.propertyId in ?1")
    List<OTA> findByHotelIdIn(List<UUID> hotelIds);


}
