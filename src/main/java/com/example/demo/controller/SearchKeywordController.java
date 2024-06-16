package com.example.demo.controller;

import com.example.demo.DTO.SearchKeywordDTO;
import com.example.demo.entity.SearchKeyword;
import com.example.demo.service.SearchKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class SearchKeywordController {

    private final SearchKeywordService searchKeywordService;

    @Autowired
    public SearchKeywordController(SearchKeywordService searchKeywordService) {
        this.searchKeywordService = searchKeywordService;
    }

    @PostMapping
    public void addKeyword(@RequestBody SearchKeywordDTO searchKeywordDTO) {
        String keyword = searchKeywordDTO.getKeyword(); // 검색어만 추출
        searchKeywordService.saveOrUpdateKeyword(keyword); // 검색어를 추가하거나 업데이트
    }

    @GetMapping
    public List<SearchKeyword> getPopularKeywords() {
        return searchKeywordService.getPopularKeywords(); // 오늘 날짜의 인기 검색어를 조회합니다.
    }
}
