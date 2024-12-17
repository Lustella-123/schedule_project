package com.sparta.schedule.Lv7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    //@Email
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "{.email}")
    private String email;
    @NotBlank
    @Size(min = 8, max = 16, message = "{.password}")
    private String password;
}