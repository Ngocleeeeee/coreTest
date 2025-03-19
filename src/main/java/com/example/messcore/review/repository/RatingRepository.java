package com.example.messcore.review.repository;

import ezcloud.message.review.Rating;
import ezcloud.message.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query("select ra from Rating ra " +
            "where ra.review = :review ")
    List<Rating> findByReview(@Param("review") Review e);
}
