package com.example.demo.controller;

import com.example.demo.entity.DailyPriceSummary;
import com.example.demo.entity.Price;
import com.example.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final PriceService priceService;
    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/product/{productId}") /* 제품 ID로 가격 변동 내역 조회 */
    public List<Price> getPricesByProductId(@PathVariable int productId) {
        return priceService.getPricesByProductId(productId);
    }

    @GetMapping("/summary/{product_id}")
    public List<DailyPriceSummary> getDailyPriceSummary(@PathVariable int product_id) {
        return priceService.getDailyPriceSummary(product_id);
    }
}
