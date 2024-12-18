package com.sparta.schedule.Lv7.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank
    @Size(min = 2, max = 10, message = "{.username}")
    private String username;

    @NotBlank
    @Email(message = "{.email}")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16, message = "{.password}")
    private String password;
}