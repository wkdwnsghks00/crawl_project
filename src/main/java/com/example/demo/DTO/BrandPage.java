package com.example.demo.DTO;

import lombok.Data;

@Data
public class BrandPage {
    private String img_url;
    private String brand_name;
    private String title;
    private int coupon_price;
    private String discount_rate;
}