package com.collegesearch.collegesearch.service;
import com.collegesearch.collegesearch.entity.ReviewsEntity;
import com.collegesearch.collegesearch.repository.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.OptionalDouble;
import java.util.List;


@Service
@AllArgsConstructor
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public ReviewsEntity addReview(ReviewsEntity review) {
        return reviewsRepository.save(review);
    }
    public List<ReviewsEntity> getReviewsByCollegeId(int collegeId) {
        return reviewsRepository.findByCollegeidId(collegeId);
    }
    public Integer getAverageRatingByCollegeId(int collegeId) {
        List<ReviewsEntity> reviews = reviewsRepository.findByCollegeidId(collegeId);
        OptionalDouble averageRating = reviews.stream().mapToDouble(ReviewsEntity::getRating).average();
        return averageRating.isPresent() ? (int) Math.round(averageRating.getAsDouble()) : null;
    }

}
