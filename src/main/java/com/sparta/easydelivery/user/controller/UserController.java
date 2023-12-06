package com.sparta.easydelivery.user.controller;

import com.sparta.easydelivery.common.StatusResponseDto;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import com.sparta.easydelivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        try {
            userService.signup(signupRequestDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new StatusResponseDto("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new StatusResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto userRequestDto, HttpServletResponse response) {
        try {
            userService.login(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));

        return ResponseEntity.ok().body(new StatusResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/users/profile")
    public ProfileResponseDto getUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUser(userDetails.getUser().getId());
    }

    @PatchMapping("/users/profile")
    public ResponseEntity<?> changeUserInfo(@RequestBody IntroduceRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            return ResponseEntity.ok(userService.changeUserInfo(requestDto,userDetails.getUser().getId()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/users/profile/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            userService.changePassword(requestDto,userDetails.getUser().getId());
            return ResponseEntity.ok(new StatusResponseDto("비밀번호 변경 완료", HttpStatus.OK.value()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
