package com.sparta.easydelivery.user.service;

import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
import com.sparta.easydelivery.user.exception.BlockedUserException;
import com.sparta.easydelivery.user.exception.DuplicatedUsernameException;
import com.sparta.easydelivery.user.exception.InvalidPasswordException;
import com.sparta.easydelivery.user.exception.InvalidTokenException;
import com.sparta.easydelivery.user.exception.NotFoundUserException;
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
                throw new InvalidTokenException();
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
            throw new DuplicatedUsernameException();
        }

        User user = new User(username, password, email, introduce, address, role, blocked);
        userRepository.save(user);
    }

    public void login(LoginRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if(user.isBlocked()){
            throw new BlockedUserException();
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

        if (passwordEncoder.matches(requestDto.getOriginPassword(), changeUser.getPassword())) {
            changeUser.changePassword(passwordEncoder.encode(requestDto.getChangePassword()));
        } else {
            throw new InvalidPasswordException();
        }

    }

    public void isAdminOrException(User user) {
        if (user.getRole() != UserRoleEnum.ADMIN) {
            throw new UnauthorizedUserException();
        }
    }

    @Transactional
    public BlockResponseDto blockedChangeUser(BlockRequsetDto requestDto, Long id) {
        User admin = findUser(id);
        isAdminOrException(admin); //관리자 체크

        User checkUsername = findUser(requestDto.getUserId());
        boolean resultBlocked = checkUsername.changeAccess();

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

        User checkUsername = findUser(requestDto.getUserId());

        UserRoleEnum userRoleEnum = checkUsername.changeRole();

        return new RoleResponseDto(userRoleEnum);
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }
}
