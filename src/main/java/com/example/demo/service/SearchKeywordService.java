package com.example.demo.service;

import com.example.demo.entity.SearchKeyword;
import com.example.demo.mapper.SearchKeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchKeywordService {

    private final SearchKeywordMapper searchKeywordMapper;

    @Autowired
    public SearchKeywordService(SearchKeywordMapper searchKeywordMapper) {
        this.searchKeywordMapper = searchKeywordMapper;
    }

    public List<SearchKeyword> getTopSearchKeywords(int limit) {
        return searchKeywordMapper.getTopSearchKeywords(limit);
    } /* 매퍼를 호출하여 카운트가 높은 순으로 정렬된 검색어 목록 가져오기 */

    public void addOrUpdateKeyword(String keyword) {
        SearchKeyword searchKeyword = searchKeywordMapper.findKeyword(keyword);
        if (searchKeyword == null) {
            searchKeyword = new SearchKeyword();
            searchKeyword.setKeyword(keyword);
            searchKeywordMapper.insertKeyword(searchKeyword);
        } else {
            searchKeywordMapper.incrementCount(searchKeyword.getId());
        }
    } /* 검색어를 데이터베이스에 저장하거나, 이미 존재하는 검색어라면 해당 검색어의 카운트 증가 */
}
