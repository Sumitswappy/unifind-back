package com.collegesearch.collegesearch.service;
import com.collegesearch.collegesearch.entity.CourseEntity;
import com.collegesearch.collegesearch.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<CourseEntity> getCourseById(int courseId) {
        return courseRepository.findById(courseId);
    }

    public void saveCourse(CourseEntity course) {
        courseRepository.save(course);
    }

    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }
}
