package com.sparta.schedule.Lv7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotBlank
    @Size(min = 2, max = 10, message = "{.username}")
    private String username;
    @NotBlank
    @Size(max = 10, message = "{.title}")
    private String title;
    @NotBlank
    @Size(max = 100, message = "{.content}")
    private String content;
}
