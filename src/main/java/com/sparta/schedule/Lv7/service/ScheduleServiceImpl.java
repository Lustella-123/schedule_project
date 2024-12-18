package com.sparta.schedule.Lv7.service;

import com.sparta.schedule.Lv7.dto.CommentRequestDto;
import com.sparta.schedule.Lv7.dto.CommentResponseDto;
import com.sparta.schedule.Lv7.dto.ScheduleRequestDto;
import com.sparta.schedule.Lv7.dto.ScheduleResponseDto;
import com.sparta.schedule.Lv7.entity.Comment;
import com.sparta.schedule.Lv7.entity.Schedule;
import com.sparta.schedule.Lv7.entity.User;
import com.sparta.schedule.Lv7.repository.CommentRepository;
import com.sparta.schedule.Lv7.repository.ScheduleRepository;
import com.sparta.schedule.Lv7.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    /**
     * 일정 생성
     *
     * @return ScheduleResponseDto
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(requestDto.getUsername());

        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContent());
        schedule.setUser(findUser);
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), findUser.getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    /**
     * 일정 조회
     *
     * @return List ScheduleResponseDto
     */
    @Override
    public List<ScheduleResponseDto> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream().map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt())).toList();
    }

    /**
     * 일정 수정
     *
     * @return Updated ScheduleResponseDto
     */
    @Override
    public ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        schedule.update(scheduleRequestDto.getTitle(), scheduleRequestDto.getContent());
        schedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    /**
     * 일정 삭제
     */
    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        scheduleRepository.delete(schedule);
    }


    /**
     * 댓글 생성
     */
    @Override
    public CommentResponseDto creatComment(Long scheduleId, CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No scheduleId"));
        Long userId = (Long) httpServletRequest.getSession(false)
                .getAttribute("sessionKey");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No userId"));

        Comment comment = new Comment(requestDto.getContent());
        comment.setSchedule(schedule);
        comment.setUser(user);

        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    /**
     * 댓글 조회
     */
    @Override
    public List<CommentResponseDto> getComment(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt()))
                .toList();
    }

    /**
     * 댓글 수정
     */
    @Override
    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        comment.setContent(requestDto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    /**
     * 댓글 삭제
     */
    @Override
    public void deleteComment(Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        commentRepository.delete(comment);
    }
}