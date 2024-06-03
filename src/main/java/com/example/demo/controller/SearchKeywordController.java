package com.example.demo.controller;

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

    @GetMapping("/top")
    public List<SearchKeyword> getTopSearchKeywords(@RequestParam int limit) {
        return searchKeywordService.getTopSearchKeywords(limit);
    } /* 서비스 레이어에서 인기 검색어 목록 받아 클라이언트로 반환 */

    @PostMapping("/add")
    public void addKeyword(@RequestParam String keyword) {
        searchKeywordService.addOrUpdateKeyword(keyword);
    } /* 검색어 받아 서비스 레이어로 전달*/
}
