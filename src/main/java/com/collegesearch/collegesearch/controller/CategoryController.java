package com.collegesearch.collegesearch.controller;

import com.collegesearch.collegesearch.entity.CategoryEntity;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(maxAge=500)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryEntity getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/colleges")
    public int getNumberOfCollegesByCategoryName(@RequestParam String categoryName) {
        return categoryService.getNumberOfCollegesByCategoryName(categoryName);
    }
    @GetMapping("/college")
    public List<CollegeEntity> getCollegesByCategoryName(@RequestParam String categoryName) {
        return categoryService.getCollegesByCategoryName(categoryName);
    }
    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.createCategory(categoryEntity);
    }

    @PutMapping("/{id}")
    public CategoryEntity updateCategory(@PathVariable int id, @RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(id, categoryEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}

