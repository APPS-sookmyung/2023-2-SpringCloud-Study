package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    
    // 중간 단계 클래스로 이동될 때 사용
    private String userId;
    private Date creatAt;
    
    private String encryptedPwd; // 패스워드 암호화

    private List<ResponseOrder> orders;
}
