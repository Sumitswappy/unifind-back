package com.collegesearch.collegesearch.entity;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String category;
    @ManyToMany
    @JoinTable(
            name = "category_course",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseEntity> categoryCourses;
}
