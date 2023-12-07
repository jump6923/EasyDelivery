package com.sparta.easydelivery.user.service;

import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
import com.sparta.easydelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String introduce = requestDto.getIntroduce();
        String address = requestDto.getAddress();
        boolean blocked = false;
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        if (introduce == null) {
            introduce = "자기 소개를 입력해 주세요";
        }
        if (address == null) {
            address = "주소를 입력해 주세요";
        }

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password, email, introduce, address, role, blocked);
        userRepository.save(user);
    }

    public void login(LoginRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(user.isBlocked()){
            throw new IllegalArgumentException("차단된 유저 입니다.");
        }
    }

    public ProfileResponseDto getUser(Long id) {
        User profileUser = findUser(id);

        return new ProfileResponseDto(profileUser);
    }

    @Transactional
    public ProfileResponseDto changeUserInfo(IntroduceRequestDto requestDto, Long id) {
        User changeUser = findUser(id);

        changeUser.changeUserInfo(requestDto);
        return new ProfileResponseDto(changeUser);
    }

    @Transactional
    public void changePassword(PasswordRequestDto requestDto, Long id) {
        User changeUser = findUser(id);

        if (requestDto.getChangePassword() != null) {
            if (requestDto.getOriginPassword() == null) {
                throw new NullPointerException("기존 비밀번호를 입력해 주세요");
            }
            if (passwordEncoder.matches(requestDto.getOriginPassword(), changeUser.getPassword())) {
                changeUser.setPassword(passwordEncoder.encode(requestDto.getChangePassword()));
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        }
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 id에 회원이 존재하지 않습니다."));
    }

    public void isAdminOrException(User user) {
        if (user.getRole() != UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("관리자만 접근 가능합니다.");
        }
    }

    @Transactional
    public BlockResponseDto blockedChangeUser(BlockRequsetDto requestDto, Long id) {
        User admin = findUser(id);
        isAdminOrException(admin); //관리자 체크
        boolean resultBlocked;

        String username = requestDto.getUsername();
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.get().isBlocked()) {
            checkUsername.get().setBlocked(false);
            resultBlocked = false;
        } else {
            checkUsername.get().setBlocked(true);
            resultBlocked = true;
        }
        return new BlockResponseDto(resultBlocked);
    }

    public List<UserResponseDto> getUserList(Long id) {
        User admin = findUser(id);
        isAdminOrException(admin); //관리자 체크

        List<User> userList = userRepository.findAll();
        List<UserResponseDto> responseDto = new ArrayList<>();

        for (User user : userList) {
            responseDto.add(new UserResponseDto(user));
        }
        return responseDto;
    }

    public UserResponseDto getUser(Long userId, Long id) {
        User admin = findUser(id);
        isAdminOrException(admin); //관리자 체크

        Optional<User> user = userRepository.findById(userId);

        return new UserResponseDto(user.get());
    }

    @Transactional
    public RoleResponseDto changeRole(RoleRequestDto requestDto, Long id){
        User admin = findUser(id);
        isAdminOrException(admin); //관리자 체크
        UserRoleEnum userRoleEnum;

        String username = requestDto.getUsername();
        Optional<User> checkUsername = userRepository.findByUsername(username);

        if (checkUsername.get().getRole()==UserRoleEnum.ADMIN) {
            checkUsername.get().setRole(UserRoleEnum.USER);
            userRoleEnum = UserRoleEnum.USER;
        } else {
            checkUsername.get().setRole(UserRoleEnum.ADMIN);
            userRoleEnum = UserRoleEnum.ADMIN;
        }
        return new RoleResponseDto(userRoleEnum);
    }
}
