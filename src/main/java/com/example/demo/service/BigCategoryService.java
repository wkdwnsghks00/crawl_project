package com.example.demo.service;

import com.example.demo.entity.BigCategory;
import com.example.demo.mapper.BigCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BigCategoryService {
    private final BigCategoryMapper bigCategoryMapper;
    @Autowired
    public BigCategoryService(BigCategoryMapper bigCategoryMapper) {
        this.bigCategoryMapper = bigCategoryMapper;
    }

    public List<BigCategory> getAllBigCategories() { /* 모든 대분류 카테고리 조회 */
        return bigCategoryMapper.findAll();
    }

}
