package com.like.minded.backend.service.user;

import com.like.minded.backend.domain.user.User;
import com.like.minded.backend.domain.user.UserRole;
import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.LoginException;
import com.like.minded.backend.exception.RegistrationException;
import com.like.minded.backend.repository.user.UserRepository;
import com.like.minded.backend.repository.user.UserRoleRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRegistrationDto userRegistrationDto) {
        validateUserRegistrationData(userRegistrationDto);

//        User newUser = UserMapper.INSTANCE.mapToUser(userRegistrationDto);
        // "user" role == 2, "admin" role == 1
        UserRole userRole = userRoleRepository.findByRoleType(2);
//        newUser.setUserRole(userRole);
        User newUser = User.builder()
                .username(userRegistrationDto.getUsername())
                .password(userRegistrationDto.getPassword())
                .email(userRegistrationDto.getEmail())
                .userRole(userRole)
                .build();

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving into Database", e);
        }

        UserResponse response = UserResponse.builder()
                .status(200)
                .message("Successfully registered user")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<UserDto>> loginUser(UserLoginDto userLoginDto) {
        User foundUser = userRepository.findByUsername(userLoginDto.getUsername());
        validateUserLoginData(userLoginDto, foundUser);

        UserDto userDto = new UserDto();
        userDto.setId(foundUser.getUserId());
        userDto.setUsername(foundUser.getUsername());
        userDto.setEmail(foundUser.getEmail());

        BaseResponse<UserDto> response = BaseResponse.<UserDto>builder()
                .status(200)
                .message("Successfully logged in.")
                .payload(userDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateUserRegistrationData(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new RegistrationException("Email already used.");
        }
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            throw new RegistrationException("Username already used.");
        }
        if (!userRegistrationDto.getConfirmPassword().equals(userRegistrationDto.getPassword())){
            throw new RegistrationException("Confirm password must be the same as password");
        }
    }

    private void validateUserLoginData(UserLoginDto userLoginDto, User foundUser) {

        if (foundUser == null) {
            throw new LoginException("User not found.");
        }

        if (!userLoginDto.getPassword().equals(foundUser.getPassword())) {
            throw new LoginException("Incorrect Password.");
        }

    }
}
