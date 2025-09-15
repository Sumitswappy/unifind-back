package com.collegesearch.collegesearch.controller;
import com.collegesearch.collegesearch.repository.CollegeRepository;
import org.springframework.http.HttpStatus;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("College")
@CrossOrigin(maxAge=500, origins = "http://localhost:3000")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;
    @Autowired
    private CollegeRepository collegeRepository;
    @GetMapping("get")
    public List<CollegeEntity> getAllColleges() {
        return collegeService.getAllColleges();
    }
    @GetMapping("getByEmail")
    public List<CollegeEntity> getCollegeByEmail(@RequestParam("email") String email) {
        return collegeService.getCollegeByEmail(email);
    }
    @GetMapping("get/{collegeId}")
    public ResponseEntity<CollegeEntity> getCollegeById(@PathVariable int collegeId) {
        return collegeService.getCollegeById(collegeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("byCourse/{course}")
    public List<CollegeEntity> getCollegesByCourse(@PathVariable int course) {
        return collegeService.getCollegesByCourse(course);
    }
    @GetMapping("byCity/{city}")
    public List<CollegeEntity> getCollegesByCity(@PathVariable String city) {
        return collegeService.getCollegesByCity(city);
    }
    @GetMapping("byState/{state}")
    public List<CollegeEntity> getCollegesByState(@PathVariable String state) {
        return collegeService.getCollegesByState(state);
    }
@GetMapping("/get-filtered")
public List<CollegeEntity> getCollegesByCityAndCourse(
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "course", required = false) String course) {

    return collegeService.getCollegesByCityAndCourse(city, course);
}
    @GetMapping("/get-filtered-state")
    public List<CollegeEntity> getCollegesByStateAndCourse(
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "course", required = false) String course) {

        return collegeService.getCollegesByStateAndCourse(state, course);
    }
    @PostMapping("add")
    public void saveCollege(@RequestBody CollegeEntity college) {
        collegeService.saveCollege(college);
    }
    @PutMapping("update/{collegeId}")
    public ResponseEntity<Void> updateCollege(@PathVariable int collegeId, @RequestBody CollegeEntity updatedCollege) {
        boolean success = collegeService.updateCollege(collegeId, updatedCollege);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/rating/{id}")
    public ResponseEntity<CollegeEntity> updateCollegeRating(@PathVariable int id, @RequestBody CollegeEntity updatedCollege) {
        return collegeRepository.findById(id)
                .map(college -> {
                    college.setRating(updatedCollege.getRating());
                    return ResponseEntity.ok(collegeRepository.save(college));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/update/profilephoto/{id}")
    public ResponseEntity<CollegeEntity> updateCollegeProfilePhoto(@PathVariable int id, @RequestBody CollegeEntity updatedCollege) {
        return collegeRepository.findById(id)
                .map(college -> {
                    college.setProfilephotoUri(updatedCollege.getProfilephotoUri());
                    college.setProfilephoto(updatedCollege.getProfilephoto());
                    return ResponseEntity.ok(collegeRepository.save(college));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/update/coverphoto/{id}")
    public ResponseEntity<CollegeEntity> updateUserProfilePhoto(@PathVariable int id, @RequestBody CollegeEntity updatedCollege) {
        return collegeRepository.findById(id)
                .map(college -> {
                    college.setCoverphotoUri(updatedCollege.getCoverphotoUri());
                    college.setCoverphoto(updatedCollege.getCoverphoto());
                    return ResponseEntity.ok(collegeRepository.save(college));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{collegeId}")
    public ResponseEntity<CollegeEntity> getCollegeWithCoursesById(@PathVariable int collegeId) {
        CollegeEntity college = collegeService.getCollegeWithCoursesById(collegeId);
        if (college != null) {
            return new ResponseEntity<>(college, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{collegeId}")
    public ResponseEntity<Void> deleteCollege(@PathVariable int collegeId) {
        collegeService.deleteCollege(collegeId);
        return ResponseEntity.noContent().build();
    }
}
