package com.example.codingchallengebackend.controller;

import com.example.codingchallengebackend.exception.ResourceNotFoundException;
import com.example.codingchallengebackend.model.Category;
import com.example.codingchallengebackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//instead of creating endpoint manually we can also use rest repositories and expose default endpoint
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public Optional<Category> getCategory(@PathVariable int id) {
        return categoryRepository.findById(id);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id ) {
        categoryRepository.deleteById(id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") int id,
                                 @RequestBody Category CategoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found for this id :: " + id));
        category.setTitle(CategoryDetails.getTitle());
        return categoryRepository.save(category);
    }

    @PostMapping("/categories/{parentId}")
    public Category createCategoryWithParent(@PathVariable(value = "parentId") int parentId,
                                 @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category parentCategory = categoryRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found for this id :: " + parentId));
        categoryDetails.setParent(parentCategory);
        return categoryRepository.save(categoryDetails);
    }
}
