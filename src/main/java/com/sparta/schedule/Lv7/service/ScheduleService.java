package com.sparta.schedule.Lv7.service;

import com.sparta.schedule.Lv7.dto.CommentRequestDto;
import com.sparta.schedule.Lv7.dto.CommentResponseDto;
import com.sparta.schedule.Lv7.dto.ScheduleRequestDto;
import com.sparta.schedule.Lv7.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface ScheduleService {
    /**
     * Schedule
     */
    //Create
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    //Read
    List<ScheduleResponseDto> getSchedule();
    //Update
    ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long id);
    //Delete
    void deleteSchedule(Long id);

    /**
     * Comment
     */
    //Create
    CommentResponseDto creatComment(Long id, CommentRequestDto requestDto);
    //Read
    List<CommentResponseDto> getComment(Long scheduleId);
    //Update
    CommentResponseDto updateComment(Long scheduleId, Long CommentId, CommentRequestDto requestDto);
    //Delete
    void deleteComment(Long scheduleId, Long CommentId);
}
