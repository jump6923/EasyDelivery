package com.sparta.easydelivery.domain.user.controller;

import com.sparta.easydelivery.domain.user.dto.BlockRequsetDto;
import com.sparta.easydelivery.domain.user.dto.UserResponseDto;
import com.sparta.easydelivery.domain.user.service.UserService;
import com.sparta.easydelivery.domain.user.dto.RoleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @PatchMapping("/block")
    public ResponseEntity<?> toggleBlockUser(@RequestBody BlockRequsetDto requestDto) {
        return ResponseEntity.ok(userService.blockedChangeUser(requestDto));
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getUserList() {
        List<UserResponseDto> responseDto = userService.getUserList();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PatchMapping("/role")
    public ResponseEntity<?> toggleRole(@RequestBody RoleRequestDto requestDto){
        return ResponseEntity.ok(userService.changeRole(requestDto));
    }

}
