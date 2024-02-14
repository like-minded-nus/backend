package com.like.minded.backend.service.profile;

import com.like.minded.backend.dto.profile.ProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.vo.profile.*;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<GetProfileResponse> getProfile(Integer id);

    public ResponseEntity<CreateProfileResponse> createProfile(UserProfileDto userProfileDto);

    public ResponseEntity<GetProfileResponse> updateProfile(UpdateUserProfileDto updateUserProfileDto);

    public ResponseEntity<CreateProfilePassionResponse> createProfilePassions(ProfilePassionDto profilePassionDto);
    public ResponseEntity<GetProfilePassionResponse> getProfilePassions(Integer profileId);

    public ResponseEntity<UpdateProfilePassionResponse> updateProfilePassions(UpdateProfilePassionDto updateProfilePassionDto);

}
