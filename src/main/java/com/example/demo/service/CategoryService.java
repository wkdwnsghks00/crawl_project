package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getAllCategories() { /* 모든 카테고리 조회 */
        return categoryMapper.findAll();
    }

    public List<Category> getCategoriesByBigCategoryId(int bigCategoryId) {
        return categoryMapper.findCategoriesByBigCategoryId(bigCategoryId);
    } /* bigcategory ID로 카테고리 조회 */

}
