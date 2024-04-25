/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.dto.user.UserUpgradePremiumDto;
import com.like.minded.backend.service.user.UserService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock private UserService userService;

    @InjectMocks private UserController userController;

    @Test
    void registerUser() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        UserResponse expectedResponse = UserResponse.builder().build();
        when(userService.registerUser(userRegistrationDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<UserResponse> response = userController.registerUser(userRegistrationDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService).registerUser(userRegistrationDto);
    }

    @Test
    void loginUser() {
        UserLoginDto userLoginDto = new UserLoginDto();
        BaseResponse<UserDto> expectedResponse = BaseResponse.<UserDto>builder().build();
        when(userService.loginUser(userLoginDto)).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<BaseResponse<UserDto>> response = userController.loginUser(userLoginDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService).loginUser(userLoginDto);
    }

    @Test
    void upgradeToPremium() {
        UserUpgradePremiumDto userUpgradePremiumDto = new UserUpgradePremiumDto();
        BaseResponse<Boolean> expectedResponse = BaseResponse.<Boolean>builder().build();
        when(userService.upgradeToPremium(userUpgradePremiumDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<BaseResponse<Boolean>> response =
                userController.upgradeToPremium(userUpgradePremiumDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService).upgradeToPremium(userUpgradePremiumDto);
    }
}
