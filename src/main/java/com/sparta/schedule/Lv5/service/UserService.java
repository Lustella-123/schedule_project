package com.sparta.schedule.Lv5.service;

import com.sparta.schedule.Lv5.dto.UserRequestDto;
import com.sparta.schedule.Lv5.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    //Create
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    //Read
    List<UserResponseDto> getUser();
    //Update
    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);
    //Delete
    void deleteUser(Long id);

    //login
    UserResponseDto login(String email, String password);
    //logout
    void logout(Long id);
}
