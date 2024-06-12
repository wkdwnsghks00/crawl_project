package com.example.demo.mapper;

import com.example.demo.entity.SearchKeyword;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SearchKeywordMapper {

    @Insert("INSERT INTO search_keyword (keyword, count, date) VALUES (#{keyword}, #{count}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SearchKeyword searchKeyword); // 새로운 검색어를 테이블에 삽입하고 ID를 자동 생성합니다.

    @Select("SELECT * FROM search_keyword WHERE keyword = #{keyword} AND date = CURRENT_DATE")
    SearchKeyword findByKeywordAndDate(@Param("keyword") String keyword); // 오늘 날짜와 일치하는 특정 검색어를 조회합니다.

    @Update("UPDATE search_keyword SET count = count + 1 WHERE id = #{id}")
    void incrementCount(@Param("id") int id); // 기존 검색어의 카운트를 증가시킵니다.

    @Select("SELECT keyword, count FROM search_keyword WHERE date = CURRENT_DATE ORDER BY count DESC")
    List<SearchKeyword> findAllOrderByCount(); // 오늘 날짜의 검색어를 카운트 순으로 정렬하여 조회합니다.

    @Update("UPDATE search_keyword SET count = 0 WHERE date < CURRENT_DATE")
    void resetKeywordCounts(); // 하루가 지나면 검색어 카운트를 0으로 초기화합니다.
}
