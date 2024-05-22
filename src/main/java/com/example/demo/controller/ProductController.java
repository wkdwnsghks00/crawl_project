package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}") /* 제품 ID로 개별 제품 조회 */
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{categoryId}")  /* 카테고리기반 제품 조회*/
    public List<Product> getProductsByCategoryId(@PathVariable int categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }
    /* 빅 카테고리기반 제품 조회*/
    @GetMapping("/bigcategory/{bigCategoryId}")
    public List<Product> getProductsByBigCategoryId(@PathVariable int bigCategoryId) {
        return productService.getProductsByBigCategoryId(bigCategoryId);
    }
    /* 브랜드별 제품 조회*/
    @GetMapping("/brand/{brandId}")
    public List<Product> getProductsByBrandId(@PathVariable int brandId) {
        return productService.getProductsByBrandId(brandId);
    }

    @GetMapping("/search") /* 제품 검색 */
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/") /* 모든 제품 조회 */
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


}
