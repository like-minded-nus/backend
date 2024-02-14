package com.like.minded.backend.vo.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProfilePassionResponse {
    private Integer status;
    private String message;
}


