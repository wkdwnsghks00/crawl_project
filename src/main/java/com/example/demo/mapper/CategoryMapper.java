package com.example.demo.mapper;

import com.example.demo.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper { /* 모든 카테고리 조회 */
    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Select("SELECT * FROM category WHERE bigcategory_id = #{bigCategoryId}")
    List<Category> findCategoriesByBigCategoryId(int bigCategoryId);  /* 대분류 ID로 카테고리 조회 */

}
