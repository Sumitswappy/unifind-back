package com.collegesearch.collegesearch.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewid;
    String reviews;
    int rating;
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    @ManyToOne
    @JoinColumn(name = "college_id")
    private CollegeEntity collegeid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userid;
}
