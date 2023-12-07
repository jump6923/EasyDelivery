package com.sparta.easydelivery.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import com.sparta.easydelivery.user.service.KakaoService;
import com.sparta.easydelivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final KakaoService kakaoService;

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

    @PatchMapping("/admin/block")
    public ResponseEntity<?> toggleBlockUser(@RequestBody BlockRequsetDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.blockedChangeUser(requestDto, userDetails.getUser().getId()));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getUserList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UserResponseDto> responseDto = userService.getUserList(userDetails.getUser().getId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.getUser(userId, userDetails.getUser().getId()));
    }

    @PatchMapping("/admin/role")
    public ResponseEntity<?> toggleRole(@RequestBody RoleRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(userService.changeRole(requestDto, userDetails.getUser().getId()));
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(String code, HttpServletResponse response)
        throws JsonProcessingException {

        String token = kakaoService.kakaoLogin(code);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
        return ResponseEntity.ok().build();
    }

}
