package com.example.messcore.review.repository;

import ezcloud.message.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository <Review, UUID>{
    @Query("select rv from Review rv " +
            "where rv.booking.externalBookingId = :id ")
    Review findByBookingByExternalBookingId(@Param("id") String id);

    @Query("select rv from Review rv " +
            "where rv.booking.propertyId in (:hotelIds) and rv.overallScore between :scoreFirst and :scoreLast and rv.title like :value")
    List<Review> fildByRequest(@Param("hotelIds") List<UUID> hotelIds, @Param("scoreFirst") Double scoreFirst, @Param("scoreLast") Double scoreLast, @Param("value") String value);
}
