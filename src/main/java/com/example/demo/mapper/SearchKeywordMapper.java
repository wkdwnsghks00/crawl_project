package com.example.demo.mapper;

import com.example.demo.entity.SearchKeyword;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SearchKeywordMapper {

    @Select("SELECT * FROM search_keyword ORDER BY count DESC LIMIT #{limit}")
    List<SearchKeyword> getTopSearchKeywords(@Param("limit") int limit); // 검색어 목록을 카운트 높은순으로 가져온다.

    @Select("SELECT * FROM search_keyword WHERE keyword = #{keyword}")
    SearchKeyword findKeyword(@Param("keyword") String keyword); //특정 검색어 존재 확인

    @Insert("INSERT INTO search_keyword(keyword, count) VALUES(#{keyword}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertKeyword(SearchKeyword searchKeyword); // 검색어 새로 삽입

    @Update("UPDATE search_keyword SET count = count + 1 WHERE id = #{id}")
    void incrementCount(@Param("id") int id); // 이미 존재하는 검색어 카운트 증가
}
