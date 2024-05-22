package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandMapper brandMapper;
    @Autowired
    public BrandService(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public List<Brand> getAllBrands() { /* 모든 브랜드 조회*/
        return brandMapper.findAllBrands();
    }
}
