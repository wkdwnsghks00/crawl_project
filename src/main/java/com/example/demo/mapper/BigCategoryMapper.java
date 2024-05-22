package com.example.demo.mapper;

import com.example.demo.entity.BigCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BigCategoryMapper {
    @Select("SELECT * FROM bigcategory")
    List<BigCategory> findAll(); /* 모든 대분류 카테고리 조회 */

}
