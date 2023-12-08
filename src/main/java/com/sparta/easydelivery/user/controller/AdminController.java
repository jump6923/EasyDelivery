package com.sparta.easydelivery.user.controller;

import com.sparta.easydelivery.user.dto.BlockRequsetDto;
import com.sparta.easydelivery.user.dto.RoleRequestDto;
import com.sparta.easydelivery.user.dto.UserResponseDto;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import com.sparta.easydelivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @PatchMapping("/block")
    public ResponseEntity<?> toggleBlockUser(@RequestBody BlockRequsetDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.blockedChangeUser(requestDto, userDetails.getUser().getId()));
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getUserList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UserResponseDto> responseDto = userService.getUserList(userDetails.getUser().getId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.getUser(userId, userDetails.getUser().getId()));
    }

    @PatchMapping("/role")
    public ResponseEntity<?> toggleRole(@RequestBody RoleRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(userService.changeRole(requestDto, userDetails.getUser().getId()));
    }

}
