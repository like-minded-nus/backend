package com.like.minded.backend.vo.profile;

import com.like.minded.backend.domain.profile.ProfilePassion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetProfilePassionResponse {
    private Integer status;
    private String message;
    private List<String> profilePassionList;
}


