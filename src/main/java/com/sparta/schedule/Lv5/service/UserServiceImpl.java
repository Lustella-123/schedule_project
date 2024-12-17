package com.sparta.schedule.Lv5.service;

import com.sparta.schedule.Lv5.dto.UserRequestDto;
import com.sparta.schedule.Lv5.dto.UserResponseDto;
import com.sparta.schedule.Lv5.entity.User;
import com.sparta.schedule.Lv5.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // UserRepository 주입
    private final HttpServletRequest httpServletRequest; // HttpServletRequest 주입

    /**
     * 유저 생성 (회원가입)
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        // username 중복 불가 검증
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        // 비밀번호 유무 검증
        if (userRequestDto.getPassword() == null || userRequestDto.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        // 이메일 유무 검증
        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        // User 엔티티 생성
        User user = new User(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getPassword());
        // 데이터베이스에 저장
        User savedUser = userRepository.save(user);

        // 결과 반환
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt());
    }

    /**
     * 유저 조회
     * @return List UserResponseDto
     */
    @Override
    public List<UserResponseDto> getUser() {
        List<User> users = userRepository.findAll();
        // User 엔티티를 UserResponseDto로 변환
        return users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt()))
                .toList();
    }

    /**
     * 유저 수정
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, Long id) {
        User user = userRepository.findById(id).orElse(null);

        // 존재하지 않는 id
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        // 사용자 정보 업데이트
        user.update(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getPassword());
        // 저장
        user = userRepository.save(user);

        // 결과 반환
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    /**
     * 유저 삭제
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        // 존재하지 않는 id
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        // 삭제
        userRepository.delete(user);
    }

    /**
     * 로그인
     */
    @Override
    public UserResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 사용자가 없습니다"));

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = httpServletRequest.getSession(true);  // 세션 생성
        session.setAttribute("sessionKey", user.getId()); // 사용자 ID 저장

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    /**
     * 로그아웃
     */
    @Override
    public void logout(Long id) {
        HttpSession session = httpServletRequest.getSession(false);  // 존재하는 세션만 가져오기
        if (session != null) {
            session.invalidate();  // 세션 무효화
        }
    }
}