package com.example.demo.config;
// 스프링 서버 전역적으로 CORS 설정
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "http://221.151.200.16:3000",
                "http://210.110.32.134:3000",
                "http://182.225.208.15:3000",
                "http://3.34.188.16:3000",
                "http://3.34.188.16"     // 필요한 경우 포트 없는 출처 추가
            ) // 허용할 출처
            .allowedMethods("OPTIONS", "GET", "POST" , "PUT", "DELETE") // 허용할 HTTP method
            .allowedHeaders("*")
            .allowCredentials(true) // 쿠키 인증 요청 허용
            .maxAge(3600); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // React의 index.html 파일을 기본 경로로 서빙
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}