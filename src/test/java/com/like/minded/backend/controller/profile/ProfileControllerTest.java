/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.profile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import com.like.minded.backend.dto.profile.ProfilePassionMatchListDto;
import com.like.minded.backend.dto.profile.ProfileResponseBodyDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.service.profile.ProfilePassionMatchService;
import com.like.minded.backend.service.profile.ProfileService;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock private ProfileService profileService;

    @Mock private ProfilePassionMatchService profilePassionMatchService;

    @InjectMocks private ProfileController profileController;

    @Test
    void getProfile() throws SQLException, IOException {
        Integer id = 1;
        BaseResponse<ProfileResponseBodyDto> baseResponse =
                BaseResponse.<ProfileResponseBodyDto>builder().build();
        when(profileService.getProfileByProfileId(id)).thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response =
                profileController.getProfile(id);

        assertEquals(OK, response.getStatusCode());
        verify(profileService).getProfileByProfileId(id);
    }

    @Test
    void getProfileByUserId() throws SQLException, IOException {
        Integer userId = 1;
        BaseResponse<ProfileResponseBodyDto> baseResponse =
                BaseResponse.<ProfileResponseBodyDto>builder().build();
        when(profileService.getProfileByUserId(userId)).thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response =
                profileController.getProfileByUserId(userId);

        assertEquals(OK, response.getStatusCode());
        verify(profileService).getProfileByUserId(userId);
    }

    @Test
    void createProfile() {
        UserProfileDto userProfileDto = new UserProfileDto();
        BaseResponse<ProfileResponseBodyDto> baseResponse =
                BaseResponse.<ProfileResponseBodyDto>builder().build();
        when(profileService.createProfile(userProfileDto))
                .thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response =
                profileController.createProfile(userProfileDto);

        assertEquals(OK, response.getStatusCode());
        verify(profileService).createProfile(userProfileDto);
    }

    @Test
    void updateProfile() {
        UpdateUserProfileDto updateUserProfileDto = new UpdateUserProfileDto();
        BaseResponse<ProfileResponseBodyDto> baseResponse =
                BaseResponse.<ProfileResponseBodyDto>builder().build();
        when(profileService.updateProfile(updateUserProfileDto))
                .thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response =
                profileController.updateProfile(updateUserProfileDto);

        assertEquals(OK, response.getStatusCode());
        verify(profileService).updateProfile(updateUserProfileDto);
    }

    @Test
    void getProfilePassionMatches() {
        Integer profileId = 1;
        BaseResponse<ProfilePassionMatchListDto> baseResponse =
                BaseResponse.<ProfilePassionMatchListDto>builder().build();
        when(profilePassionMatchService.getProfilePassionMatches(profileId))
                .thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<ProfilePassionMatchListDto>> response =
                profileController.getProfilePassionMatches(profileId);

        assertEquals(OK, response.getStatusCode());
        verify(profilePassionMatchService).getProfilePassionMatches(profileId);
    }

    @Test
    void getProfilesByProfileIds() throws SQLException, IOException {
        Integer profileId1 = 1, profileId2 = 2;
        BaseResponse<List<ProfileResponseBodyDto>> baseResponse =
                BaseResponse.<List<ProfileResponseBodyDto>>builder().build();
        when(profileService.getProfilesByProfileIds(profileId1, profileId2))
                .thenReturn(ResponseEntity.ok(baseResponse));

        ResponseEntity<BaseResponse<List<ProfileResponseBodyDto>>> response =
                profileController.getProfilesByProfileIds(profileId1, profileId2);

        assertEquals(OK, response.getStatusCode());
        verify(profileService).getProfilesByProfileIds(profileId1, profileId2);
    }
}
