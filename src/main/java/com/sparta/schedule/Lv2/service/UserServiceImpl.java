package com.sparta.schedule.Lv2.service;

import com.sparta.schedule.Lv2.dto.UserRequestDto;
import com.sparta.schedule.Lv2.dto.UserResponseDto;
import com.sparta.schedule.Lv2.entity.User;
import com.sparta.schedule.Lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = new User(userRequestDto.getUsername(), userRequestDto.getEmail());
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

        user.update(userRequestDto.getUsername(), userRequestDto.getEmail());
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
