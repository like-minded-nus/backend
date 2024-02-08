package com.like.minded.backend.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
