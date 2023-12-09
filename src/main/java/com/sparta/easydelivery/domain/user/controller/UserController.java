package com.sparta.easydelivery.domain.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.easydelivery.domain.user.dto.LoginRequestDto;
import com.sparta.easydelivery.domain.user.dto.PasswordRequestDto;
import com.sparta.easydelivery.domain.user.dto.SignupRequestDto;
import com.sparta.easydelivery.domain.user.service.UserService;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.domain.user.dto.IntroduceRequestDto;
import com.sparta.easydelivery.domain.user.implement.UserDetailsImpl;
import com.sparta.easydelivery.domain.user.service.KakaoService;
import com.sparta.easydelivery.domain.user.service.NaverService;
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
    private final KakaoService kakaoService;
    private final NaverService naverService;

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

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(String code, HttpServletResponse response)
        throws JsonProcessingException {

        String token = kakaoService.kakaoLogin(code);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/naver/callback")
    public ResponseEntity<?> naverLogin(String code, HttpServletResponse response)
        throws JsonProcessingException {

        String token = naverService.naverLogin(code);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return ResponseEntity.ok().build();
    }

}
