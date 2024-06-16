package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
    private String id;      // 사용자 ID
    private String nickname; // 닉네임
    private String password; // 비밀번호
}
