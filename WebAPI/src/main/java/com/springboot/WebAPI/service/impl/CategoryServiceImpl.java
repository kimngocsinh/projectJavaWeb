package com.springboot.WebAPI.service.impl;

import com.springboot.WebAPI.model.Category;
import com.springboot.WebAPI.repository.CategoryRepository;
import com.springboot.WebAPI.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category updateCategory = categoryRepository.findById(id).map(newCategory ->{
            newCategory.setName(category.getName());
            return categoryRepository.save(newCategory);
        }).orElseGet(() ->{
            return categoryRepository.save(category);
        });
        return categoryRepository.save(updateCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        boolean exists = categoryRepository.existsById(id);
        if (exists){
            categoryRepository.deleteById(id);
        }
    }
}
