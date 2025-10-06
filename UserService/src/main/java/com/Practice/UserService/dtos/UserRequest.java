package com.Practice.UserService.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
