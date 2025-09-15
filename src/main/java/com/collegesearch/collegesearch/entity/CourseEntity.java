package com.collegesearch.collegesearch.entity;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
     String course;
    @ManyToMany(mappedBy = "collegeCourses")
     Set<CollegeEntity> college;
    @ManyToMany(mappedBy = "categoryCourses")
    Set<CategoryEntity> category;

}
