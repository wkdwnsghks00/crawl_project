package com.example.demo.DTO;

import lombok.Data;

@Data
public class ProductPage {
    private int id;
    private String title;
    private String prod_option;
    private String description;
    private float rating;
    private String img_url;
    private String url;
    private int category_id;
    private int brand_id;

    //price
    private int origin_price;
    private int sale_price;
    private int coupon_price;
    private String discount_rate;
    private String delivery;

    //brand
    private String brand_name;
}