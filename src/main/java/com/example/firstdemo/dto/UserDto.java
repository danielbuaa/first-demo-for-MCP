package com.example.firstdemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel
 * @since 2025-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    
    @NotBlank(message = "Username cannot be empty")
    private String username;
    
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phone;
    
    private boolean active = true;
}