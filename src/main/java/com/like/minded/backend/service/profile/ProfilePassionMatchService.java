package com.like.minded.backend.service.profile;

import com.like.minded.backend.dto.profile.ProfilePassionMatchListDto;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface ProfilePassionMatchService {
    public ResponseEntity<BaseResponse<ProfilePassionMatchListDto>> getProfilePassionMatches(Integer profileId);
}
