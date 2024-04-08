package com.like.minded.backend.service.user;

import com.like.minded.backend.domain.user.User;
import com.like.minded.backend.domain.user.UserRole;
import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.repository.user.UserRepository;
import com.like.minded.backend.repository.user.UserRoleRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
        assertEquals("Successfully registered user", Objects.requireNonNull(response.getBody()).getMessage());

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

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully logged in.", Objects.requireNonNull(response.getBody()).getMessage());
        assertNotNull(response.getBody().getPayload());
        assertEquals("testUser", response.getBody().getPayload().getUsername());

        verify(userRepository, times(1)).findByUsername("testUser");
    }
}