package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 사용자 정보를 저장, 비밀번호는 암호화 처리
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        userMapper.insertUser(user);
    }

    // 사용자 ID로 사용자 정보를 가져옴
    public User getUserById(String id) {
        return userMapper.findById(id);
    }

    // 사용자 자격 증명을 검증
    public boolean validateUserCredentials(String id, String rawPassword) {
        User user = userMapper.findById(id);
        return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
