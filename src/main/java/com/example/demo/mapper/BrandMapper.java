package com.example.demo.mapper;

import com.example.demo.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandMapper {
    @Select("SELECT * FROM brand")
    List<Brand> findAllBrands(); /* 모든 브랜드 조회 */
}