package com.example.demo.scheduler;

import com.example.demo.mapper.SearchKeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyKeywordResetScheduler {

    private final SearchKeywordMapper searchKeywordMapper;

    @Autowired
    public DailyKeywordResetScheduler(SearchKeywordMapper searchKeywordMapper) {
        this.searchKeywordMapper = searchKeywordMapper;
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void resetDailyKeywordCounts() {
        searchKeywordMapper.resetKeywordCounts(); // 검색어 카운트 초기화
    }
}
