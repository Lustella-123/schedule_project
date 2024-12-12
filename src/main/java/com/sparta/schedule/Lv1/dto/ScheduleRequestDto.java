package com.sparta.schedule.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private final String username;
    private final String title;
    private final String content;
}
