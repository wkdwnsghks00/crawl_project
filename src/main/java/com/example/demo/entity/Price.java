package com.example.demo.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Price {
    private int id;
    private int origin_price;
    private int sale_price;
    private int coupon_price;
    private String discount_rate;
    private LocalDateTime crawl_time;
    private int product_id;

}

