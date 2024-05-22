package com.example.demo.mapper;

import com.example.demo.entity.Price;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PriceMapper {
    @Select("SELECT * FROM price WHERE product_id = #{productId}")
    List<Price> findPricesByProductId(int productId); /* 제품 ID로 가격 변동 내역 조회 */


}

