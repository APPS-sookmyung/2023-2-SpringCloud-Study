package com.example.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data //setter, getter 따로 만들고 싶지 않을 때 추가
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자 만들어줌
public class Greeting {
    @Value("${greeting.message}")
    private String message;
}
