/* LikeMinded (C)2024 */
package com.like.minded.backend.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.user.User;
import com.like.minded.backend.domain.user.UserRole;
import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.dto.user.UserUpgradePremiumDto;
import com.like.minded.backend.repository.user.UserRepository;
import com.like.minded.backend.repository.user.UserRoleRepository;
import com.like.minded.backend.service.ban.BanService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;

    @Mock private UserRoleRepository userRoleRepository;

    @InjectMocks private UserServiceImpl userService;

    @Mock private BanService banService;

    @Test
    void registerUserSuccessfully() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("testUser");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setConfirmPassword("password");
        userRegistrationDto.setEmail("test@example.com");

        UserRole userRole = new UserRole(2, "user");

        when(userRoleRepository.findByRoleType(2)).thenReturn(userRole);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        ResponseEntity<UserResponse> response = userService.registerUser(userRegistrationDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "Successfully registered user",
                Objects.requireNonNull(response.getBody()).getMessage());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void loginUserSuccessfully() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUsername("testUser");
        userLoginDto.setPassword("password");

        User foundUser = new User();
        foundUser.setUserId(1);
        foundUser.setUsername("testUser");
        foundUser.setPassword("password");
        foundUser.setEmail("test@example.com");
        foundUser.setUserRole(new UserRole(2, "User"));

        when(userRepository.findByUsername("testUser")).thenReturn(foundUser);

        ResponseEntity<BaseResponse<UserDto>> response = userService.loginUser(userLoginDto);
        //        when(banService.findIsUserBanned(1)).thenReturn(false);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "Successfully logged in.", Objects.requireNonNull(response.getBody()).getMessage());
        assertNotNull(response.getBody().getPayload());
        assertEquals("testUser", response.getBody().getPayload().getUsername());

        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void upgradeToPremiumUserExists() {
        User user = new User();
        user.setUserId(1);
        user.setIsPremium(0);

        UserUpgradePremiumDto userUpgradePremiumDto = new UserUpgradePremiumDto();
        userUpgradePremiumDto.setUserId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        doAnswer(
                        invocation -> {
                            User u = invocation.getArgument(0);
                            assertEquals(1, u.getIsPremium());
                            return null;
                        })
                .when(userRepository)
                .save(any(User.class));

        ResponseEntity<BaseResponse<Boolean>> response =
                userService.upgradeToPremium(userUpgradePremiumDto);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().getPayload());
        assertEquals("Successfully upgraded to premium.", response.getBody().getMessage());
    }

    @Test
    void upgradeToPremiumUserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<BaseResponse<Boolean>> response =
                userService.upgradeToPremium(new UserUpgradePremiumDto(1));

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
