package com.like.minded.backend.service.user;

import com.like.minded.backend.dto.user.UserDto;
import com.like.minded.backend.dto.user.UserLoginDto;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import com.like.minded.backend.dto.user.UserUpgradePremiumDto;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.user.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<UserResponse> registerUser(UserRegistrationDto userRegistrationDto);

    public ResponseEntity<BaseResponse<UserDto>> loginUser(UserLoginDto userLoginDto);

    public ResponseEntity<BaseResponse<Boolean>> upgradeToPremium(UserUpgradePremiumDto userDto);
}
