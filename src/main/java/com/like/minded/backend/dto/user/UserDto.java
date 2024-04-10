/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private Integer userRole;
    private Integer isPremium;
}
