package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final com.sparta.schedule.repository.ScheduleRepository ScheduleRepository;

    /**
     * 일정 생성
     * @return ResponseDto 반환
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getUsername(), requestDto.getTitle(), requestDto.getContent());
        Schedule savedSchedule = ScheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getUsername(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getCreatedAt(), savedSchedule.getUpdatedAt());
    }

    /**
     * 일정 조회
     * @return List ResponseDto 반환
     */
    @Override
    public List<ScheduleResponseDto> getSchedule() {
        List<Schedule> schedules = ScheduleRepository.findAll();
        return schedules.stream().map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt())).toList();
    }

    /**
     * 일정 수정
     * @return Updated ResponseDto 반환
     */
    @Override
    public ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long id) {
        Schedule schedule = ScheduleRepository.findById(id).orElse(null);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        schedule.update(scheduleRequestDto.getUsername(), scheduleRequestDto.getTitle(), scheduleRequestDto.getContent());
        schedule = ScheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule.getId(), schedule.getUsername(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    /**
     * 일정 삭제
     */
    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = ScheduleRepository.findById(id).orElse(null);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No id");
        }

        ScheduleRepository.delete(schedule);
    }
}
