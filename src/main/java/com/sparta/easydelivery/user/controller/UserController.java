package com.sparta.easydelivery.user.controller;

import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import com.sparta.easydelivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto userRequestDto, HttpServletResponse response) {
        userService.login(userRequestDto);

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.getUser(userDetails.getUser().getId()));
    }

    @PatchMapping("/profile")
    public ResponseEntity<?> changeUserInfo(@RequestBody IntroduceRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.ok(userService.changeUserInfo(requestDto, userDetails.getUser().getId()));
    }

    @PatchMapping("/profile/password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid PasswordRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.changePassword(requestDto, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }
}
