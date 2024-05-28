package com.example.demo.service;

import com.example.demo.DTO.BigCategoryPage;
import com.example.demo.DTO.BrandPage;
import com.example.demo.DTO.CategoryPage;
import com.example.demo.DTO.MainPage;
import com.example.demo.DTO.ProductPage;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<MainPage> getAllProducts() {
        return productMapper.findAllProducts();
    } /* ★메인 홈화면 (제품추천순 제품전체) */

    public List<BigCategoryPage> getProductsByBigCategoryId(int bigCategoryId) {
        return productMapper.findProductsByBigCategoryId(bigCategoryId);
    } /* 큰 카테고리 별 페이지 */

    public List<CategoryPage> getProductsByCategoryId(int categoryId) {
        return productMapper.findProductsByCategoryId(categoryId);
    } /* 카테고리 별 페이지 */

    public List<BrandPage> getProductsByBrandId(int brandId) {
        return productMapper.findProductsByBrandId(brandId);
    } /* 브랜드별 페이지*/

    public ProductPage getProductById(int id) {
        return productMapper.findProductById(id);
    } /* 개별 제품 상세 페이지 */











    public List<Product> searchProducts(String keyword) { /* 제품 검색 */
        return productMapper.searchProducts(keyword);
    } /* 제품 검색 기능*/



}