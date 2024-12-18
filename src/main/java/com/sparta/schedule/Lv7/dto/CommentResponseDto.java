package com.sparta.schedule.Lv7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private final Long id;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
