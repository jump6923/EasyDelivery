package com.sparta.easydelivery.user.service;

import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.user.dto.*;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
import com.sparta.easydelivery.user.exception.DuplicatedUsernameException;
import com.sparta.easydelivery.user.exception.InvalidPasswordException;
import com.sparta.easydelivery.user.exception.InvalidTokenException;
import com.sparta.easydelivery.user.exception.NotFoundUserException;
import com.sparta.easydelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        User user = new User(username, password, email, introduce, address, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
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
            changeUser.setPassword(passwordEncoder.encode(requestDto.getChangePassword()));
        } else {
            throw new InvalidPasswordException();
        }

    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

    public void isAdminOrException(User user) {
        if (user.getRole() != UserRoleEnum.ADMIN) {
            throw new UnauthorizedUserException();
        }
    }
}
