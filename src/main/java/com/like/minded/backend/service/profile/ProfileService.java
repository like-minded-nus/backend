package com.like.minded.backend.service.profile;

import com.like.minded.backend.dto.profile.*;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;

public interface ProfileService {
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByProfileId(Integer profileId) throws SQLException, IOException;

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByUserId(Integer userId) throws SQLException, IOException;

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> createProfile(UserProfileDto userProfileDto);

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> updateProfile(UpdateUserProfileDto updateUserProfileDto);

}
