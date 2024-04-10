/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Integer status;
    private String message;
}
