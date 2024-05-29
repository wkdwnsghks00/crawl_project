package com.example.demo.mapper;
import com.example.demo.DTO.BigCategoryPage;
import com.example.demo.DTO.BrandPage;
import com.example.demo.DTO.CategoryPage;
import com.example.demo.DTO.MainPage;
import com.example.demo.DTO.ProductPage;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface ProductMapper {
    @Select("SELECT p.img_url, b.name AS brand_name, p.title, pr.coupon_price, pr.discount_rate " +
        "FROM product p " +
        "JOIN (SELECT * FROM price ORDER BY crawl_time DESC) pr ON p.id = pr.product_id " +
        "JOIN brand b ON p.brand_id = b.id " +
        "GROUP BY p.id, p.img_url, b.name, p.title, pr.coupon_price, pr.discount_rate " +
        //"ORDER BY CAST(REPLACE(pr.discount_rate, '%', '') AS UNSIGNED) DESC " + // 할인순 정렬
        "LIMIT 60") // 상위 60개만 가져오기
    List<MainPage> findAllProducts(); /* ★메인 홈화면 (제품추천순 제품전체) */



    @Select("SELECT p.img_url, b.name AS brand_name, p.title, pr.coupon_price, pr.discount_rate " +
        "FROM product p " +
        "JOIN (SELECT * FROM price ORDER BY crawl_time DESC) pr ON p.id = pr.product_id " +
        "JOIN brand b ON p.brand_id = b.id " +
        "JOIN category c ON p.category_id = c.id " +
        "WHERE c.bigcategory_id = #{bigCategoryId} " +
        "GROUP BY p.id, p.img_url, b.name, p.title, pr.coupon_price, pr.discount_rate " +
        //"ORDER BY CAST(REPLACE(pr.discount_rate, '%', '') AS UNSIGNED) DESC "+ // 할인순 정렬
        "LIMIT 60") // 상위 60개만 가져오기
    List<BigCategoryPage> findProductsByBigCategoryId(int bigCategoryId); /* 큰 카테고리 별 페이지 */


    @Select("SELECT p.img_url, b.name AS brand_name, p.title, pr.coupon_price, pr.discount_rate " +
        "FROM product p " +
        "JOIN (SELECT * FROM price ORDER BY crawl_time DESC) pr ON p.id = pr.product_id " +
        "JOIN brand b ON p.brand_id = b.id " +
        "WHERE p.category_id = #{categoryId} " +
        "GROUP BY p.id, p.img_url, b.name, p.title, pr.coupon_price, pr.discount_rate " +
        //"ORDER BY CAST(REPLACE(pr.discount_rate, '%', '') AS UNSIGNED) DESC "+ // 할인순 정렬
        "LIMIT 60") // 상위 60개만 가져오기
    List<CategoryPage> findProductsByCategoryId(int categoryId); /* 카테고리별 페이지 */


    @Select("SELECT p.img_url, b.name AS brand_name, p.title, pr.coupon_price, pr.discount_rate " +
        "FROM product p " +
        "JOIN (SELECT * FROM price ORDER BY crawl_time DESC) pr ON p.id = pr.product_id " +
        "JOIN brand b ON p.brand_id = b.id " +
        "WHERE p.brand_id = #{brandId} " +
        "GROUP BY p.id, p.img_url, b.name, p.title, pr.coupon_price, pr.discount_rate " +
        //"ORDER BY CAST(REPLACE(pr.discount_rate, '%', '') AS UNSIGNED) DESC "+ // 할인순 정렬
        "LIMIT 60") // 상위 60개만 가져오기
    List<BrandPage> findProductsByBrandId(int brandId); /* 브랜드별 페이지 */


    @Select("SELECT p.id, p.title, p.prod_option, p.description, p.rating, p.img_url, p.url, p.category_id, p.brand_id, " +
        "pr.origin_price, pr.sale_price, pr.coupon_price, pr.discount_rate, pr.delivery, " +
        "b.name AS brand_name " +
        "FROM product p " +
        "JOIN (SELECT * FROM price WHERE product_id = #{id} ORDER BY crawl_time DESC LIMIT 1) pr " +
        "ON p.id = pr.product_id " +
        "JOIN brand b ON p.brand_id = b.id " +
        "WHERE p.id = #{id}")
    ProductPage findProductById(int id); /* 개별 제품 상세 페이지 */

}
