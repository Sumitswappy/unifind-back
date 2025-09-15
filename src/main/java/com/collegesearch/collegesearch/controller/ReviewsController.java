package com.collegesearch.collegesearch.controller;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.entity.ReviewsEntity;
import com.collegesearch.collegesearch.repository.ReviewsRepository;
import com.collegesearch.collegesearch.response.ReviewResponse;
import com.collegesearch.collegesearch.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Date;

@RestController
@RequestMapping("reviews")
@CrossOrigin(maxAge=500)
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;
    @Autowired
    private ReviewsRepository reviewsRepository;

    @PostMapping("add")
    public ResponseEntity<?> addReview(@RequestBody ReviewsEntity review) {
        try {
            // Check if a review with the same user ID and college ID already exists
            boolean reviewExists = reviewsRepository.existsByUseridAndCollegeid(review.getUserid(), review.getCollegeid());
            if (reviewExists) {
                return new ResponseEntity<>("You have already submitted a review for this college.", HttpStatus.BAD_REQUEST);
            }

            review.setReviewDate(new Date());
            ReviewsEntity newReview = reviewsService.addReview(review);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add review.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/college/{collegeId}")
    public List<ReviewResponse> getReviewsByCollegeId(@PathVariable int collegeId) {
        // Assuming you have a service method that retrieves reviews by collegeId
        List<ReviewsEntity> reviews = reviewsService.getReviewsByCollegeId(collegeId);

        // Create a list of ReviewResponse objects containing the required fields
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (ReviewsEntity review : reviews) {
            ReviewResponse response = new ReviewResponse();
            response.setReviewid(review.getReviewid());
            response.setFirstName(review.getUserid().getFirstName());
            response.setLastName(review.getUserid().getLastName());
            response.setEmail(review.getUserid().getEmail());
            response.setReview(review.getReviews());
            response.setRating(review.getRating());
            response.setDate(review.getReviewDate());
            reviewResponses.add(response);
        }

        return reviewResponses;
    }
    @GetMapping("/get-rating/{collegeId}")
    public ResponseEntity<Integer> getAverageRatingByCollegeId(@PathVariable int collegeId) {
        // Assuming you have a service method that retrieves reviews by collegeId and calculates the average rating
        Integer averageRating = reviewsService.getAverageRatingByCollegeId(collegeId);

        if (averageRating != null) {
            return ResponseEntity.ok(averageRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
