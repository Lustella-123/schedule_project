package com.sparta.schedule.Lv4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private String username;
    private String title;
    private String content;
}
