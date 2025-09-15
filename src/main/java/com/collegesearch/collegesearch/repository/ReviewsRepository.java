package com.collegesearch.collegesearch.repository;


import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.entity.ReviewsEntity;
import com.collegesearch.collegesearch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
    // Check if a review with the same user ID and college ID exists
    boolean existsByUseridAndCollegeid(UserEntity userId, CollegeEntity collegeId);
    List<ReviewsEntity> findByCollegeidId(int collegeId);

    List<ReviewsEntity> findByUseridId(int userId);
}

