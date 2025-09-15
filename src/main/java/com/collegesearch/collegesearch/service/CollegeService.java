package com.collegesearch.collegesearch.service;

import com.collegesearch.collegesearch.Util.SecurityManager;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.entity.UserEntity;
import com.collegesearch.collegesearch.repository.CollegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;
    private SecurityManager securityManager;
    public List<CollegeEntity> getAllColleges() {
        return collegeRepository.findAll();
    }
    public List<CollegeEntity> getCollegesByState(String state) {
        // Implement the logic to fetch colleges by state from your repository
        return collegeRepository.findByState(state);
    }
    public List<CollegeEntity> getCollegesByCity(String city) {
        // Implement the logic to fetch colleges by state from your repository
        return collegeRepository.findByCity(city);
    }
public List<CollegeEntity> getCollegesByCityAndCourse(String city, String course) {
    return collegeRepository.findByCityAndCourse(city, course);
}
    public List<CollegeEntity> getCollegesByStateAndCourse(String state, String course) {
        return collegeRepository.findByStateAndCourse(state, course);
    }
    public List<CollegeEntity> getCollegesByCourse(int course) {
        return collegeRepository.findByCourse(course);
    }
//    public int getNumberOfCollegesByCourse(String courseName) {
//        List<CollegeEntity> colleges = collegeRepository.findByCourse(courseName);
//        return colleges.size();
//    }

    public Optional<CollegeEntity> getCollegeById(int collegeId) {
        return collegeRepository.findById(collegeId);
    }
    public CollegeEntity getCollegeWithCoursesById(int collegeId) {
        Optional<CollegeEntity> collegeOptional = collegeRepository.findCollegeWithCoursesById(collegeId);
        return collegeOptional.orElse(null);
    }
    public void saveCollege(CollegeEntity college) {
        //college.setPassword(securityManager.encryptPassword(college.getPassword()));
        collegeRepository.save(college);
    }
    public List<CollegeEntity> getCollegeByEmail(String email) {
        return collegeRepository.findByEmail(email);
    }
    public boolean updateCollege(int collegeId, CollegeEntity updatedCollege) {
        Optional<CollegeEntity> existingCollegeOptional = collegeRepository.findById(collegeId);
        if (existingCollegeOptional.isPresent()) {
            CollegeEntity existingCollege = existingCollegeOptional.get();

            // Update the college details
            existingCollege.setName(updatedCollege.getName());
            existingCollege.setFirstName(updatedCollege.getFirstName());
            existingCollege.setLastName(updatedCollege.getLastName());
            existingCollege.setEmail(updatedCollege.getEmail());
            existingCollege.setPhoneNumber(updatedCollege.getPhoneNumber());
            existingCollege.setAddress(updatedCollege.getAddress());
            existingCollege.setCity(updatedCollege.getCity());
            existingCollege.setState(updatedCollege.getState());
            existingCollege.setAffiliation(updatedCollege.getAffiliation());
            existingCollege.setCertification(updatedCollege.getCertification());
            existingCollege.setEstablishmentYear(updatedCollege.getEstablishmentYear());
            existingCollege.setCollegeweb(updatedCollege.getCollegeweb());
            existingCollege.setApplyweb(updatedCollege.getApplyweb());

            // Check if brochurefileUri is empty or null
            if (updatedCollege.getBrochurefileUri() != null && !updatedCollege.getBrochurefileUri().isEmpty()) {
                existingCollege.setBrochurefileUri(updatedCollege.getBrochurefileUri());
            }
            // Check if rating is empty or null
            if (updatedCollege.getRating() != null ) {
                existingCollege.setRating(updatedCollege.getRating());
            }
            // Check if filename is empty or null
            if (updatedCollege.getFilename() != null && !updatedCollege.getFilename().isEmpty()) {
                existingCollege.setFilename(updatedCollege.getFilename());
            }

            // Check if collegeCourses is empty or null
            if (updatedCollege.getCollegeCourses() != null && !updatedCollege.getCollegeCourses().isEmpty()) {
                existingCollege.setCollegeCourses(updatedCollege.getCollegeCourses());
            }

            // Save the updated college entity
            collegeRepository.save(existingCollege);
            return true;
        } else {
            return false; // College not found
        }
    }



    public void deleteCollege(int collegeId) {
        collegeRepository.deleteById(collegeId);
    }
}
