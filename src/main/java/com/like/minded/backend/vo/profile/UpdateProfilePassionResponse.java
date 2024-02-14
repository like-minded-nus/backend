package com.like.minded.backend.vo.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateProfilePassionResponse {
    private Integer status;
    private String message;
}


