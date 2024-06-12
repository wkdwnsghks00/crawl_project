package com.example.demo.mapper;

import com.example.demo.entity.DailyPriceSummary;
import com.example.demo.entity.Price;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PriceMapper {
    @Select("SELECT * FROM price WHERE product_id = #{productId} ORDER BY crawl_time ASC")
    List<Price> findPricesByProductId(int productId); /* 제품의 가격정보 전체를 오름차순(과거부터 최신)정렬 */

    @Select("WITH LatestCrawl AS ( " +
        "    SELECT DATE_FORMAT(crawl_time, '%Y-%m-%d') AS date, " +
        "           MAX(crawl_time) AS latest_crawl_time " +
        "    FROM price " +
        "    WHERE product_id = #{product_id} " +
        "    GROUP BY DATE_FORMAT(crawl_time, '%Y-%m-%d') " +
        "), " +
        "FilteredPrices AS ( " +
        "    SELECT p.product_id, p.coupon_price, DATE_FORMAT(p.crawl_time, '%Y-%m-%d') AS date, " +
        "           ROW_NUMBER() OVER (PARTITION BY p.product_id, DATE_FORMAT(p.crawl_time, '%Y-%m-%d') " +
        "                             ORDER BY p.crawl_time DESC) AS rn " +
        "    FROM price p " +
        "    JOIN LatestCrawl lc ON DATE_FORMAT(p.crawl_time, '%Y-%m-%d') = lc.date " +
        "                         AND p.crawl_time <= lc.latest_crawl_time " +
        "), " +
        "AggregatedData AS ( " +
        "    SELECT product_id, date, " +
        "           MAX(coupon_price) AS high_price, " +
        "           MIN(coupon_price) AS low_price, " +
        "           COUNT(*) AS count " +
        "    FROM FilteredPrices " +
        "    GROUP BY product_id, date " +
        ") " +
        "SELECT product_id, date AS crawl_time, " +
        "       high_price, " +
        "       low_price " +
        "FROM AggregatedData " +
        "WHERE product_id = #{product_id}")
    List<DailyPriceSummary> getDailyPriceSummary(@Param("product_id") int product_id); // 제품의 일별 가격 요약을 조회합니다.
}

