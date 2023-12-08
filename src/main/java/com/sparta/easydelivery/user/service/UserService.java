package com.sparta.easydelivery.user.service;

import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.user.entity.PasswordHistory;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
import com.sparta.easydelivery.user.exception.*;
import com.sparta.easydelivery.user.repository.PasswordHistoryRepository;
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
    private final PasswordHistoryRepository passwordHistoryRepository;
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
        passwordHistoryRepository.save(new PasswordHistory(user,password));
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

        List<PasswordHistory> passwordHistories = passwordHistoryRepository
                .findAllByUserOrderByCreatedAtDesc(changeUser);

        String password = changeUser.getPassword();
        String changePassword = requestDto.getChangePassword();

        if (!passwordEncoder.matches(requestDto.getOriginPassword(), password)) {
            throw new InvalidPasswordException();
        }

        for (PasswordHistory passwordHistory : passwordHistories) {
            if (passwordEncoder.matches(changePassword, passwordHistory.getPassword())) {
                throw new DuplicatedPasswordException();
            }
        }

        String encodePassword = passwordEncoder.encode(changePassword);
        changeUser.changePassword(encodePassword);
        if (passwordHistories.size() == 3) {
            passwordHistoryRepository.delete(passwordHistories.get(2));
        }
        PasswordHistory passwordHistory = new PasswordHistory(changeUser, encodePassword);
        passwordHistoryRepository.save(passwordHistory);
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
        return new BlockResponseDto(checkUsername.changeAccess());
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
        return new RoleResponseDto(checkUsername.changeRole());
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }
}
