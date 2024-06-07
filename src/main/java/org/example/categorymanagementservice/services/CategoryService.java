package org.example.categorymanagementservice.services;

import org.example.categorymanagementservice.dtos.CategoryDTO;
import org.example.categorymanagementservice.entities.Category;
import org.example.categorymanagementservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(CategoryDTO category,int id) {
        Category c = categoryRepository.findById(id);
        c.setName(category.getName());
        c.setDate(category.getDate());
        return categoryRepository.save(c);
    }
    public Category findByID(int id) {
        return categoryRepository.findById(id);
    }
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
