package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/") /* 모든 카테고리 조회 */
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/bigcategory/{bigCategoryId}") /* 대분류 ID로 카테고리 조회 */
    public List<Category> getCategoriesByBigCategoryId(@PathVariable int bigCategoryId) {
        return categoryService.getCategoriesByBigCategoryId(bigCategoryId);
    }

}
