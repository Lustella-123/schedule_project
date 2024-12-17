package com.sparta.schedule.Lv5.service;

import com.sparta.schedule.Lv5.dto.ScheduleRequestDto;
import com.sparta.schedule.Lv5.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    //Create
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    //Read
    List<ScheduleResponseDto> getSchedule();
    //Update
    ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long id);
    //Delete
    void deleteSchedule(Long id);
}
