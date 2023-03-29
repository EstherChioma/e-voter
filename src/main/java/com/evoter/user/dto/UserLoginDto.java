package com.evoter.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
}
