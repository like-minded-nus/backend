package com.like.minded.backend.vo.profile;

import com.like.minded.backend.dto.profile.ProfilePassionMatchListDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfilePassionMatchResponse {
        private Integer status;
        private String message;
        private ProfilePassionMatchListDto profilePassionMatchList;
}
