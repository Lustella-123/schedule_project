package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private String username;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    }

