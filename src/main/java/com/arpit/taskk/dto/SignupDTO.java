package com.arpit.taskk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

}
