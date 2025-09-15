package com.collegesearch.collegesearch.repository;

import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Integer> {
    @Query("SELECT c FROM CollegeEntity c JOIN c.collegeCourses co WHERE c.city = :city AND co.course = :course")
    List<CollegeEntity> findByCityAndCourse(@Param("city") String city, @Param("course") String course);
    @Query("SELECT c FROM CollegeEntity c JOIN c.collegeCourses co WHERE c.state = :state AND co.course = :course")
    List<CollegeEntity> findByStateAndCourse(@Param("state") String state, @Param("course") String course);
    @Query( "SELECT c FROM CollegeEntity c JOIN c.collegeCourses co WHERE co.id = :course")
    List<CollegeEntity> findByCourse(@Param("course") int course);
    @Query("SELECT c FROM CollegeEntity c JOIN FETCH c.collegeCourses WHERE c.id = :collegeId")
    Optional<CollegeEntity> findCollegeWithCoursesById(@Param("collegeId") int collegeId);
    List<CollegeEntity> findByCity(@Param("city") String city);
    List<CollegeEntity> findByState(@Param("state") String state);
//    @Query("SELECT c FROM CollegeEntity c JOIN c.collegeCourses cc JOIN cc.course co WHERE c.city = :city AND co.course = :course")
//    List<CollegeEntity> findByCityAndCourse(@Param("city") String city, @Param("course") String course);
   List<CollegeEntity>findByEmail(String Email);
}

