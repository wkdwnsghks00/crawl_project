package com.example.demo.DTO;

import lombok.Data;

@Data
public class MainPage {
    private int id;
    private String title;
    private String img_url;
    private String brand_name;
    //price
    private int origin_price;
    private int sale_price;
    private int coupon_price;
    private String discount_rate;
    private String delivery;
    private int review_count;

    private String prod_option;
    private String description;
    private float rating;
    private String url;
    private int category_id;
    private int brand_id;

    private int price_change_rate; // 추가된 필드
    private boolean isLowestPrice; // 역대 최저가 여부 추가

}
