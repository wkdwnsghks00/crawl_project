package com.example.demo.service;

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

    public Product getProductById(int id) { /* 제품 ID로 제품 조회 */
        return productMapper.findProductById(id);
    }

    public List<Product> getProductsByCategoryId(int categoryId) { /* 카테고리기반 제품 조회 */
        return productMapper.findProductsByCategoryId(categoryId);
    }

    public List<Product> getProductsByBigCategoryId(int bigCategoryId) { /* 빅 카테고리기반 제품 조회*/
        return productMapper.findProductsByBigCategoryId(bigCategoryId);
    }

    public List<Product> getProductsByBrandId(int brandId) { /* 브랜드별 제품 조회*/
        return productMapper.findProductsByBrandId(brandId);
    }

    public List<Product> searchProducts(String keyword) { /* 제품 검색 */
        return productMapper.searchProducts(keyword);
    } /* 제품 검색 기능*/

    public List<Product> getAllProducts() { /* 모든 제품 조회 */
        return productMapper.findAllProducts();
    } /* 전체 제품 목록 조회 */

}