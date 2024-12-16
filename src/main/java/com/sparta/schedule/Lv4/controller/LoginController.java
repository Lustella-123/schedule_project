package com.sparta.schedule.Lv4.controller;

import com.sparta.schedule.Lv4.dto.LoginRequestDto;
import com.sparta.schedule.Lv4.dto.UserResponseDto;
import com.sparta.schedule.Lv4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody LoginRequestDto requestDto
    ) {
        return new ResponseEntity<>(userService.login(requestDto.getEmail(), requestDto.getPassword()), HttpStatus.OK);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        userService.logout(null);  // 세션 무효화
        return new ResponseEntity<>("로그아웃되었습니다.", HttpStatus.OK);
    }
}
