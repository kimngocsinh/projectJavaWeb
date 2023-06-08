package com.springboot.WebAPI.controller;

import com.springboot.WebAPI.model.Category;
import com.springboot.WebAPI.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @PostMapping("/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category addCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(addCategory, HttpStatus.OK);
    }
    @PutMapping("/update-category/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") Long id){
        Category updateCategory = categoryService.updateCategory(category, id);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }
    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
