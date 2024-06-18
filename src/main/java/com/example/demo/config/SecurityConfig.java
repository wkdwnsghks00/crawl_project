package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호를 비활성화합니다.
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/users/signup", "/api/users/login", "/api/keywords", "/api/keywords/**").permitAll() // 회원가입 및 키워드 API는 인증 없이 접근 가능
                .anyRequest().authenticated() // 나머지 요청은 인증이 필요합니다.
            )
            .formLogin(form -> form
                .loginPage("/login") // 로그인 페이지 경로
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // HTTP Basic 인증을 활성화합니다.

        return http.build();
    }
}
