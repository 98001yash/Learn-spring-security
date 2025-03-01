package com.company.SecurityApp.SecurityApplication.controllers;


import com.company.SecurityApp.SecurityApplication.dto.LoginDto;
import com.company.SecurityApp.SecurityApplication.dto.LoginResponseDto;
import com.company.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.company.SecurityApp.SecurityApplication.dto.UserDto;
import com.company.SecurityApp.SecurityApplication.services.AuthService;
import com.company.SecurityApp.SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{

    private final AuthService authService;
    private final UserService userService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PutMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

            Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        String refreshToken  = Arrays.stream(request.getCookies())
                .filter(cookie ->"refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the Cookie"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }

}