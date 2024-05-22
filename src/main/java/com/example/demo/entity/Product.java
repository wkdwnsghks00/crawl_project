package com.example.demo.entity;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String title;
    private String prod_option;
    private String description;
    private String img_url;
    private String url;
    private int category_id;
    private int brand_id;
}
