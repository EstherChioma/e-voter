package com.evoter.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty
    private String name;
    @NotNull(message = "Age cannot be null")
    private Integer age;
    @NotEmpty(message = "NIN is required")
    private String nin;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
    private String gender;
}
