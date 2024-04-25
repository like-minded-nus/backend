/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.user;

import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.dto.user.UserUpgradePremiumDto;
import com.like.minded.backend.service.user.UserService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.registerUser(userRegistrationDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<BaseResponse<UserDto>> loginUser(@RequestBody UserLoginDto userLoginDto) {
        return userService.loginUser(userLoginDto);
    }

    @PutMapping(path = "/upgradetopremium")
    public ResponseEntity<BaseResponse<Boolean>> upgradeToPremium(
            @RequestBody UserUpgradePremiumDto userUpgradePremiumDto) {
        return userService.upgradeToPremium(userUpgradePremiumDto);
    }
}
