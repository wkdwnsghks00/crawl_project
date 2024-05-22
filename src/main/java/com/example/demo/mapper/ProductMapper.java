package com.example.demo.mapper;
import com.example.demo.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findProductById(int id); /* 제품 ID로 개별 제품 조회 */

    @Select("SELECT * FROM product WHERE category_id = #{categoryId}")
    List<Product> findProductsByCategoryId(int categoryId); /* 카테고리기반 제품 조회 */

    @Select("SELECT * FROM product WHERE category_id IN (SELECT id FROM category WHERE bigcategory_id = #{bigCategoryId})")
    List<Product> findProductsByBigCategoryId(int bigCategoryId);/* 빅 카테고리기반 제품 조회*/

    @Select("SELECT * FROM product WHERE brand_id = #{brandId}")
    List<Product> findProductsByBrandId(int brandId); /* 브랜드별 제품 조회*/


    @Select("SELECT * FROM product WHERE title LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchProducts(String keyword); /* 제품 검색 기능 */

    @Select("SELECT * FROM product")
    List<Product> findAllProducts(); /* 전체 제품 목록 조회 */
}
