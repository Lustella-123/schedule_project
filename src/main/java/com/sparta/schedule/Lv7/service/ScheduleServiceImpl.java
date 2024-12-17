package com.sparta.schedule.Lv7.service;

import com.sparta.schedule.Lv7.dto.ScheduleRequestDto;
import com.sparta.schedule.Lv7.dto.ScheduleResponseDto;
import com.sparta.schedule.Lv7.entity.Schedule;
import com.sparta.schedule.Lv7.entity.User;
import com.sparta.schedule.Lv7.repository.ScheduleRepository;
import com.sparta.schedule.Lv7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /**
     * 일정 생성
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
     * @return List ScheduleResponseDto
     */
    @Override
    public List<ScheduleResponseDto> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream().map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getUser().getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt())).toList();
    }

    /**
     * 일정 수정
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
}
