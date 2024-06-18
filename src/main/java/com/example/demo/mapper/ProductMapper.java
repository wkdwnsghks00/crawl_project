package com.example.demo.mapper;

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
    @Select("WITH LatestHour AS ( " +
        "    SELECT DATE_FORMAT(MAX(crawl_time), '%Y-%m-%d %H:00:00') AS latest_hour " +
        "    FROM price " +
        "), " +
        "LatestPrices AS ( " +
        "    SELECT product_id, coupon_price AS latest_price, discount_rate, delivery, origin_price, sale_price, crawl_time, review_count, " +
        "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY crawl_time DESC) AS rn, " +
        "           (SELECT MIN(coupon_price) FROM price WHERE product_id = p.product_id AND crawl_time < (SELECT latest_hour FROM LatestHour)) AS previous_lowest_price " +
        "    FROM price p " +
        "    WHERE DATE_FORMAT(crawl_time, '%Y-%m-%d %H:00:00') = (SELECT latest_hour FROM LatestHour) " +
        "), " +
        "PreviousPrices AS ( " +
        "    SELECT product_id, coupon_price AS previous_price, crawl_time, " +
        "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY crawl_time DESC) AS rn " +
        "    FROM price " +
        ") " +
        "SELECT p.id, p.img_url, b.name AS brand_name, p.title, p.prod_option, p.description, p.rating, p.url, p.category_id, p.brand_id, " +
        "       pr1.latest_price AS coupon_price, pr1.discount_rate, pr1.delivery, pr1.origin_price, pr1.sale_price, pr1.review_count, " +
        "       ROUND(((pr1.latest_price - pr2.previous_price) / pr2.previous_price) * 100, 1) AS price_change_rate, " +
        "       (pr1.latest_price < pr1.previous_lowest_price) AS isLowestPrice " +
        "FROM product p " +
        "JOIN LatestPrices pr1 ON p.id = pr1.product_id AND pr1.rn = 1 " +
        "JOIN PreviousPrices pr2 ON p.id = pr2.product_id AND pr2.rn = 2 " +
        "JOIN brand b ON p.brand_id = b.id " +
        "ORDER BY price_change_rate ASC")
    List<MainPage> findAllProducts();
    /* ★메인 홈화면 (제품추천순 제품전체) */


//    @Select("WITH LatestPrices AS ( " +
//        "    SELECT product_id, coupon_price AS latest_price, discount_rate, delivery, origin_price, sale_price, crawl_time, " +
//        "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY crawl_time DESC) AS rn " +
//        "    FROM price " +
//        "), " +
//        "PreviousPrices AS ( " +
//        "    SELECT product_id, coupon_price AS previous_price, crawl_time, " +
//        "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY crawl_time DESC) AS rn " +
//        "    FROM price " +
//        ") " +
//        "SELECT p.id, p.img_url, b.name AS brand_name, p.title, p.prod_option, p.description, p.rating, p.url, p.category_id, p.brand_id, " +
//        "       pr1.latest_price AS coupon_price, pr1.discount_rate, pr1.delivery, pr1.origin_price, pr1.sale_price, " +
//        "       ((pr2.previous_price - pr1.latest_price) / pr2.previous_price) * 100 AS price_change_rate " +
//        "FROM product p " +
//        "JOIN LatestPrices pr1 ON p.id = pr1.product_id AND pr1.rn = 1 " +
//        "JOIN PreviousPrices pr2 ON p.id = pr2.product_id AND pr2.rn = 2 " +
//        "JOIN brand b ON p.brand_id = b.id " +
//        "WHERE p.category_id = #{categoryId} " +
//        "ORDER BY price_change_rate DESC")
//    List<CategoryPage> findProductsByCategoryId(int categoryId); /* 카테고리별 페이지 */

//    @Select("SELECT p.id, p.img_url, b.name AS brand_name, p.title, p.prod_option, p.description, p.rating, p.url, p.category_id, p.brand_id, " +
//        "pr.coupon_price, pr.discount_rate, pr.origin_price, pr.sale_price, pr.delivery " +
//        "FROM product p " +
//        "JOIN (SELECT product_id, coupon_price, discount_rate, origin_price, sale_price, delivery " +
//        "      FROM price " +
//        "      WHERE (product_id, crawl_time) IN " +
//        "            (SELECT product_id, MAX(crawl_time) " +
//        "             FROM price " +
//        "             GROUP BY product_id)) pr ON p.id = pr.product_id " +
//        "JOIN brand b ON p.brand_id = b.id " +
//        "WHERE p.brand_id = #{brandId} " +
//        "ORDER BY CAST(REPLACE(pr.discount_rate, '%', '') AS DECIMAL) DESC ")
//    List<BrandPage> findProductsByBrandId(int brandId); /* 브랜드별 페이지 */

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
