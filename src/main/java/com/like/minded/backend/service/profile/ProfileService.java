/* LikeMinded (C)2024 */
package com.like.minded.backend.service.profile;

import com.like.minded.backend.dto.profile.*;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByProfileId(
            Integer profileId) throws SQLException, IOException;

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByUserId(Integer userId)
            throws SQLException, IOException;

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> createProfile(
            UserProfileDto userProfileDto);

    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> updateProfile(
            UpdateUserProfileDto updateUserProfileDto);

    ResponseEntity<BaseResponse<List<ProfileResponseBodyDto>>> getProfilesByProfileIds(
            Integer profileId1, Integer profileId2) throws SQLException, IOException;
}
