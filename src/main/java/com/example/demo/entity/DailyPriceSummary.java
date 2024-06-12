package com.example.demo.entity;

import lombok.Data;

@Data
public class DailyPriceSummary {
    private String crawl_time; // 날짜 (yyyy-MM-dd)
    private int product_id; // 제품 ID
    private int high_price; // 최고가
    private int low_price; // 최저가
}
