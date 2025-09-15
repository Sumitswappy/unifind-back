package com.collegesearch.collegesearch.entity;

import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "college")
public class CollegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    @Column(unique = true)
    private String email;
    private String address;
    private String city;
    private String state;
    private String affiliation;
    private String certification;
    private Long establishmentYear;
    private String filename;
    private String brochurefileUri;
    private String profilephoto;
    private String profilephotoUri;
    private String coverphoto;
    private String coverphotoUri;
    private String collegeweb;
    private String applyweb;
    private Double rating;

    @ManyToMany
    @JoinTable(
            name = "college_course",
            joinColumns = @JoinColumn(name = "college_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseEntity> collegeCourses;

    // getters and setters
}
