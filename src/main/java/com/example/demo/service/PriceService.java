package com.example.demo.service;

import com.example.demo.entity.Price;
import com.example.demo.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    private final PriceMapper priceMapper;
    @Autowired
    public PriceService(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public List<Price> getPricesByProductId(int productId) { /* 제품 ID로 가격 변동 내역 조회 */
        return priceMapper.findPricesByProductId(productId);
    }

}

