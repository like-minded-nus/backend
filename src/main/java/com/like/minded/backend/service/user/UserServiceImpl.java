/* LikeMinded (C)2024 */
package com.like.minded.backend.service.user;

import com.like.minded.backend.domain.user.User;
import com.like.minded.backend.domain.user.UserRole;
import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.dto.user.UserUpgradePremiumDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.LoginException;
import com.like.minded.backend.exception.RegistrationException;
import com.like.minded.backend.repository.user.UserRepository;
import com.like.minded.backend.repository.user.UserRoleRepository;
import com.like.minded.backend.service.ban.BanService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    BanService banService;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRegistrationDto userRegistrationDto) {
        validateUserRegistrationData(userRegistrationDto);

        UserRole userRole = userRoleRepository.findByRoleType(2);
        User newUser =
                User.builder()
                        .username(userRegistrationDto.getUsername())
                        .password(userRegistrationDto.getPassword())
                        .email(userRegistrationDto.getEmail())
                        .userRole(userRole)
                        .isPremium(0)
                        .build();

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving into Database", e);
        }

        UserResponse response =
                UserResponse.builder().status(200).message("Successfully registered user").build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<UserDto>> loginUser(UserLoginDto userLoginDto) {
        BaseResponse<UserDto> response;

        UserDto userDto = new UserDto();
        User foundUser = userRepository.findByUsername(userLoginDto.getUsername());
        if (banService.findIsUserBanned(foundUser.getUserId())) {
            userDto.setId(foundUser.getUserId());
            userDto.setUsername(foundUser.getUsername());
            userDto.setEmail(foundUser.getEmail());
            userDto.setUserRole(foundUser.getUserRole().getRoleType());
            userDto.setIsPremium(foundUser.getIsPremium());
            userDto.setBanned(true);
            response =
                    BaseResponse.<UserDto>builder()
                            .status(200)
                            .message("User is banned.")
                            .payload(userDto)
                            .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        validateUserLoginData(userLoginDto, foundUser);

        userDto.setId(foundUser.getUserId());
        userDto.setUsername(foundUser.getUsername());
        userDto.setEmail(foundUser.getEmail());
        userDto.setUserRole(foundUser.getUserRole().getRoleType());
        userDto.setIsPremium(foundUser.getIsPremium());
        userDto.setBanned(false);

        response =
                BaseResponse.<UserDto>builder()
                        .status(200)
                        .message("Successfully logged in.")
                        .payload(userDto)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<Boolean>> upgradeToPremium(
            UserUpgradePremiumDto userUpgradePremiumDto) {
        Optional<User> optionalUser = userRepository.findById(userUpgradePremiumDto.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User foundUser = optionalUser.get();
        foundUser.setIsPremium(1);
        BaseResponse<Boolean> response;
        try {
            userRepository.save(foundUser);
            response =
                    BaseResponse.<Boolean>builder()
                            .status(200)
                            .message("Successfully upgraded to premium.")
                            .payload(true)
                            .build();
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving into database", e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateUserRegistrationData(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new RegistrationException("Email already used.");
        }
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            throw new RegistrationException("Username already used.");
        }
        if (!userRegistrationDto.getConfirmPassword().equals(userRegistrationDto.getPassword())) {
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
