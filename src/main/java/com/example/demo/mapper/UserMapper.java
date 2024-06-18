package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 사용자 정보를 데이터베이스에 삽입
    @Insert("INSERT INTO user (id, nickname, password) VALUES (#{id}, #{nickname}, #{password})")
    void insertUser(User user);

    // 사용자 ID로 사용자 정보를 조회
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") String id);
}