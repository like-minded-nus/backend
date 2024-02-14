package com.like.minded.backend.vo.profile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProfileResponse {
    private Integer status;
    private String message;
    private Integer profileId;
}