package com.example.demo.entity;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SearchKeyword {
    private int id; // 검색어의 고유 ID.
    private String keyword; // 검색어 문자열.
    private int count; // 검색된 횟수.
    private LocalDate date; // 검색어가 입력된 날짜.
}
