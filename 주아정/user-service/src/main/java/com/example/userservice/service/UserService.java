package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll(); // db에서 바로 가져옴 // 가공할 거면 UserDto로 바꿔도 가능

    UserDto getUserDetailsByEmail(String userName);
}
