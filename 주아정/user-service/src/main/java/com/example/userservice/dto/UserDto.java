package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    
    // 중간 단계 클래스로 이동될 때 사용
    private String userId;
    private Date creatAt;
    
    private String encryptedPwd; // 패스워드 암호화
}
