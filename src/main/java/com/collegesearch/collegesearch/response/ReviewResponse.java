package com.collegesearch.collegesearch.response;

import lombok.Data;

import java.util.Date;
@Data
public class ReviewResponse {
    private int reviewid;
    private String firstName;
    private String lastName;
    private String email;
    private String review;
    private int rating;
    private Date date;
}
