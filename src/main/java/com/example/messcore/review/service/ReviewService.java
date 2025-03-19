package com.example.messcore.review.service;

import com.example.messcore.common.dto.Res;
import com.example.messcore.common.service.CrsService;
import com.example.messcore.customer.repository.CustomerRepository;
import com.example.messcore.customer.repository.OtaRepository;
import com.example.messcore.hotel.repository.HotelRepository;
import com.example.messcore.messsage.dto.BookingDto;
import com.example.messcore.messsage.repository.BookingRepository;
import com.example.messcore.review.entity.ChannexReviewList;
import com.example.messcore.review.mapper.BookingMapper;
import com.example.messcore.review.repository.BookingPaymentRepository;
import com.example.messcore.review.repository.CommentRepository;
import com.example.messcore.review.repository.RatingRepository;
import com.example.messcore.review.repository.ReviewRepository;
import ezcloud.message.booking.Booking;
import ezcloud.message.booking.Customer;
import ezcloud.message.hotel.Hotel;
import ezcloud.message.ota.OTA;
import ezcloud.message.review.Comment;
import ezcloud.message.review.Rating;
import ezcloud.message.review.Review;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final RatingRepository ratingRepository;
    private final CommentRepository commentRepository;
    private final HotelRepository hotelRepository;
    private final OtaRepository oTARepository;
    private final CustomerRepository customerRepository;
    private final BookingPaymentRepository bookingPaymentRepository;
    private final CrsService crsService;
    private final BookingMapper bookingMapper;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, RatingRepository ratingRepository, CommentRepository commentRepository, HotelRepository hotelRepository, OtaRepository oTARepository, CustomerRepository customerRepository, BookingPaymentRepository bookingPaymentRepository, CrsService crsService, BookingMapper bookingMapper) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.ratingRepository = ratingRepository;
        this.commentRepository = commentRepository;
        this.hotelRepository = hotelRepository;
        this.oTARepository = oTARepository;
        this.customerRepository = customerRepository;
        this.bookingPaymentRepository = bookingPaymentRepository;
        this.crsService = crsService;
        this.bookingMapper = bookingMapper;
    }

    public Res saveReviewList(ChannexReviewList channexReviewList) {
        channexReviewList.getData().forEach(rv -> {
            if (rv.getRelationships().getBooking() == null) return;
            Review review =  reviewRepository.findByBookingByExternalBookingId(rv.getRelationships().getBooking().getData().getId());
            if (review == null)  {
                Optional<Booking> optionalBooking = bookingRepository.findBookingByExternalBookingId(rv.getRelationships().getBooking().getData().getId());
                Booking booking;
                Review newReview  = new Review();
                if (optionalBooking.isPresent()) {
                    booking = optionalBooking.get();

                    newReview.setBooking(booking);
                    newReview.setBookingCode(booking.getBookingCode());
                    newReview.setCustomer(booking.getCustomer());
                    newReview.setPropertyId(UUID.fromString(rv.getRelationships().getProperty().getData().getId()));
                    newReview.setPropertyType(rv.getRelationships().getProperty().getData().getType());
                    newReview.setOverallScore(rv.getAttributes().getOverall_score());
                    newReview.setHidden(rv.getAttributes().is_hidden());
                    newReview.setTitle(booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName());
                    newReview.setCustomerCode(booking.getCustomer().getCustomerCode());
                    newReview.setCreatedDate(LocalDateTime.parse(rv.getAttributes().getInserted_at()));
                    newReview.setUpdatedDate(LocalDateTime.parse(rv.getAttributes().getUpdated_at()));
                    newReview.setExternalReviewCode(rv.getId().toString());
                    reviewRepository.saveAndFlush(newReview);
                    rv.getAttributes().getScores().forEach(s -> {
                        Rating rating = new Rating();
                        rating.setReview(newReview);
                        rating.setScore(s.getScore());
                        rating.setTitle(s.getCategory());
                        ratingRepository.save(rating);
                    });
                    Comment comment = new Comment();
                    comment.setReview(newReview);
                    comment.setCustomer(booking.getCustomer());
                    comment.setContent(rv.getAttributes().getContent());
                    commentRepository.save(comment);
                } else {
                    BookingDto bookingDto = crsService.getBookingCrs(rv.getRelationships().getBooking().getData().getId());
                    if (bookingDto ==  null) return;
                    if (bookingDto.getBookingPayments() == null)
                        bookingDto.setBookingPayments(new ArrayList<>());
                    booking = bookingMapper.toEntity(bookingDto);
                    Optional<Hotel> hotel = hotelRepository.findByHotelCode(bookingDto.getPropertyCode());
                    if (hotel.isEmpty()) return;
                    booking.setPropertyId(hotel.get().getId());
                    OTA ota = oTARepository.findByOtaCode(bookingDto.getOtaCode());
                    booking.setOta(ota);
                    Customer customer = booking.getCustomer();
                    customerRepository.saveAndFlush(customer);
                    booking.setCustomer(customer);
                    bookingRepository.save(booking);
                    bookingPaymentRepository.saveAll(booking.getBookingPayments());
                    newReview.setBooking(booking);
                    newReview.setBookingCode(booking.getBookingCode());
                    newReview.setCustomer(booking.getCustomer());
                    newReview.setPropertyId(UUID.fromString(rv.getRelationships().getProperty().getData().getId()));
                    newReview.setPropertyType(rv.getRelationships().getProperty().getData().getType());
                    newReview.setOverallScore(rv.getAttributes().getOverall_score());
                    newReview.setHidden(rv.getAttributes().is_hidden());
                    newReview.setTitle(booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName());
                    newReview.setCustomerCode(booking.getCustomer().getCustomerCode());
                    newReview.setCreatedDate(LocalDateTime.parse(rv.getAttributes().getInserted_at()));
                    newReview.setUpdatedDate(LocalDateTime.parse(rv.getAttributes().getUpdated_at()));
                    newReview.setExternalReviewCode(rv.getId().toString());
                    reviewRepository.saveAndFlush(newReview);
                    Comment comment = new Comment();
                    comment.setReview(newReview);
                    comment.setCustomer(booking.getCustomer());
                    comment.setContent(rv.getAttributes().getContent());
                    commentRepository.save(comment);
                    rv.getAttributes().getScores().forEach(s -> {
                        Rating rating = new Rating();
                        rating.setReview(newReview);
                        rating.setScore(s.getScore());
                        rating.setTitle(s.getCategory());
                        ratingRepository.save(rating);
                    });
                }
            };

        });
        return new Res(Res.STATUS_OK, "Success");
    }
}
