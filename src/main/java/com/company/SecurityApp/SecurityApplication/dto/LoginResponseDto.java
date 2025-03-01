package com.company.SecurityApp.SecurityApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private Long ud;
    private String accessToken;
    private String refreshToken;
}
