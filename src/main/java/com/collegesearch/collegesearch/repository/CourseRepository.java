package com.collegesearch.collegesearch.repository;

import com.collegesearch.collegesearch.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

}
