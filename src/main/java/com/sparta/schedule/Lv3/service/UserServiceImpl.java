package com.sparta.schedule.Lv3.service;

import com.sparta.schedule.Lv3.dto.UserRequestDto;
import com.sparta.schedule.Lv3.dto.UserResponseDto;
import com.sparta.schedule.Lv3.entity.User;
import com.sparta.schedule.Lv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 유저 생성
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        //username 중복 불가 검증
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        //비밀번호 유무 검증
        if (userRequestDto.getPassword() == null || userRequestDto.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        //이메일 유무 검증
        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        User user = new User(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getPassword());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt());
    }

    /**
     * 유저 조회
     * @return List UserResponseDto
     */
    @Override
    public List<UserResponseDto> getUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt())).toList();
    }

    /**
     * 유저 수정
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        user.update(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getPassword());
        user = userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    /**
     * 유저 삭제
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        userRepository.delete(user);
    }
}
