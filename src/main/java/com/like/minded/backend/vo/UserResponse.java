package com.like.minded.backend.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Integer status;
    private String message;

}
