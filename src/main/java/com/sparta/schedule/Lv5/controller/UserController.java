package com.sparta.schedule.Lv5.controller;

import com.sparta.schedule.Lv5.dto.UserRequestDto;
import com.sparta.schedule.Lv5.dto.UserResponseDto;
import com.sparta.schedule.Lv5.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 유저 생성 (회원가입)
     * @return 201 CREATED
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.saveUser(userRequestDto),HttpStatus.CREATED);
    }

    /**
     * 유저 조회
     * @return 200 OK
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUser() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    /**
     * 유저 수정
     * @return 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(userService.updateUser(userRequestDto, id), HttpStatus.OK);
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}