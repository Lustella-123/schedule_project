package com.sparta.schedule.Lv1.controller;

import com.sparta.schedule.Lv1.dto.ScheduleRequestDto;
import com.sparta.schedule.Lv1.dto.ScheduleResponseDto;
import com.sparta.schedule.Lv1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     * @return 201 CREATED 반환
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto ) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    /**
     * 일정 조회
     * @return 200 OK 반환
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule() {
        return new ResponseEntity<>(scheduleService.getSchedule(), HttpStatus.OK);
    }

    /**
     * 일정 수정
     * @return 200 OK 반환
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @RequestBody ScheduleRequestDto scheduleRequestDto,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleRequestDto, id), HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @return 200 OK 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}