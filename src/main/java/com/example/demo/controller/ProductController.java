package com.example.demo.controller;

import com.example.demo.DTO.BrandPage;
import com.example.demo.DTO.CategoryPage;
import com.example.demo.DTO.MainPage;
import com.example.demo.DTO.ProductPage;
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

    @GetMapping("/")
    public List<MainPage> getAllProducts() {
        return productService.getAllProducts();
    } /* ★메인 홈화면 (제품추천순 제품전체) */


//    @GetMapping("/category/{categoryId}")
//    public List<CategoryPage> getProductsByCategoryId(@PathVariable int categoryId) {
//        return productService.getProductsByCategoryId(categoryId);
//    } /* 카테고리 별 페이지*/


//    @GetMapping("/brand/{brandId}")
//    public List<BrandPage> getProductsByBrandId(@PathVariable int brandId) {
//        return productService.getProductsByBrandId(brandId);
//    } /* 브랜드별 페이지*/

    @GetMapping("/{id}")
    public ProductPage getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    } /* 개별 제품 상세 페이지 */

}
