package com.collegesearch.collegesearch.repository;

import com.collegesearch.collegesearch.entity.CategoryEntity;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query("SELECT COUNT(DISTINCT co) FROM CategoryEntity c " +
            "JOIN c.categoryCourses cc " +
            "JOIN cc.college co " +
            "WHERE c.category = :categoryName")
    int countCollegesByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT co FROM CategoryEntity c " +
            "JOIN c.categoryCourses cc " +
            "JOIN cc.college co " +
            "WHERE c.category = :categoryName")
    List<CollegeEntity> findCollegesByCategoryName(@Param("categoryName") String categoryName);


}

