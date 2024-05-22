package com.example.demo.controller;

import com.example.demo.entity.BigCategory;
import com.example.demo.service.BigCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bigcategories")
public class BigCategoryController {
    private final BigCategoryService bigCategoryService;
    @Autowired
    public BigCategoryController(BigCategoryService bigCategoryService) {
        this.bigCategoryService = bigCategoryService;
    }

    @GetMapping("/")
    public List<BigCategory> getAllBigCategories() { /* 모든 대분류 카테고리 조회 */
        return bigCategoryService.getAllBigCategories(); /* ex pc카테고리 or 디지털 */
    }

}
