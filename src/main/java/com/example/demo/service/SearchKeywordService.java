package com.example.demo.service;

import com.example.demo.entity.SearchKeyword;
import com.example.demo.mapper.SearchKeywordMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SearchKeywordService {

    private final SearchKeywordMapper searchKeywordMapper;

    @Autowired
    public SearchKeywordService(SearchKeywordMapper searchKeywordMapper) {
        this.searchKeywordMapper = searchKeywordMapper;
    }

    public void saveOrUpdateKeyword(String keyword) {
        SearchKeyword existingKeyword = searchKeywordMapper.findByKeywordAndDate(keyword); // 오늘 날짜의 특정 검색어를 조회합니다.
        if (existingKeyword != null) {
            searchKeywordMapper.incrementCount(existingKeyword.getId()); // 새로운 검색어 객체를 생성합니다.
        } else {
            SearchKeyword newKeyword = new SearchKeyword(); // 새로운 검색어 객체를 생성합니다.
            newKeyword.setKeyword(keyword); // 검색어를 설정합니다.
            newKeyword.setCount(1); // 카운트를 1로 설정합니다.
            newKeyword.setDate(LocalDate.now()); // 오늘 날짜를 설정합니다.
            searchKeywordMapper.insert(newKeyword); // 검색어를 테이블에 삽입합니다.
        }
    }

    public List<SearchKeyword> getPopularKeywords() {
        return searchKeywordMapper.findAllOrderByCount(); // 오늘 날짜의 인기 검색어를 조회합니다.
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void deleteOldKeywords() {
        searchKeywordMapper.deleteOldKeywords();
    }
}
