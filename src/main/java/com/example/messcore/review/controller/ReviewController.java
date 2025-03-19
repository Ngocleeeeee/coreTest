package com.example.messcore.review.controller;

import com.example.messcore.common.dto.Res;
import com.example.messcore.review.entity.ChannexReviewList;
import com.example.messcore.review.service.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channex/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/get-list")
    public Res getReviewList(@RequestBody ChannexReviewList channexReviewList) {
        return reviewService.saveReviewList(channexReviewList);
    }

}
