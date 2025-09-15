
package com.collegesearch.collegesearch.service;

import com.collegesearch.collegesearch.entity.CategoryEntity;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.repository.CategoryRepository;
import com.collegesearch.collegesearch.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;


@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(int id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    public int getNumberOfCollegesByCategoryName(String categoryName) {
        return categoryRepository.countCollegesByCategoryName(categoryName);
    }
    public List<CollegeEntity> getCollegesByCategoryName(String categoryName) {
        return categoryRepository.findCollegesByCategoryName(categoryName);
    }

    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity updateCategory(int id, CategoryEntity categoryEntity) {
        categoryEntity.setId(id); // Set the ID of the category to update
        return categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}

