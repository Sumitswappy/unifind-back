package com.collegesearch.collegesearch.controller;

import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.entity.CourseEntity;
import com.collegesearch.collegesearch.repository.CollegeRepository;
import com.collegesearch.collegesearch.repository.CourseRepository;
import com.collegesearch.collegesearch.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("courses")
@CrossOrigin(maxAge=500)
public class CourseController {

    @Autowired
    private CourseService courseService;
    private CourseRepository courseRepository;
    @GetMapping("get")
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("get/{courseId}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable int courseId) {
        return courseService.getCourseById(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("add")
    public void saveCourse(@RequestBody CourseEntity course) {
        courseService.saveCourse(course);
    }

    @DeleteMapping("get/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
