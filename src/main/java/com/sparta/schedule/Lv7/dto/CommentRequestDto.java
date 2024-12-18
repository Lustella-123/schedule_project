package com.sparta.schedule.Lv7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank
    @Size(max = 100, message = "{.content}")
    private String content;
}
