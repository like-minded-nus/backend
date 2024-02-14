package com.like.minded.backend.service.profile;

import com.like.minded.backend.vo.profile.ProfilePassionMatchResponse;
import org.springframework.http.ResponseEntity;

public interface ProfilePassionMatchService {
    public ResponseEntity<ProfilePassionMatchResponse> getProfilePassionMatches(Integer profileId);
}
