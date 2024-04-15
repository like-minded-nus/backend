/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.profile;

import com.like.minded.backend.dto.profile.*;
import com.like.minded.backend.service.profile.ProfilePassionMatchService;
import com.like.minded.backend.service.profile.ProfileService;
import com.like.minded.backend.service.report.ReportService;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired private ProfileService profileService;
    @Autowired private ReportService reportService;

    @Autowired private ProfilePassionMatchService profilePassionMatchService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfile(@PathVariable Integer id)
            throws SQLException, IOException {
        return profileService.getProfileByProfileId(id);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByUserId(
            @PathVariable Integer userId) throws SQLException, IOException {
        return profileService.getProfileByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> createProfile(
            @RequestBody UserProfileDto userProfileDto) {
        return profileService.createProfile(userProfileDto);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> updateProfile(
            @RequestBody UpdateUserProfileDto updateUserProfileDto) {
        return profileService.updateProfile(updateUserProfileDto);
    }

    @GetMapping("/passions/match/{profileId}")
    public ResponseEntity<BaseResponse<ProfilePassionMatchListDto>> getProfilePassionMatches(
            @PathVariable Integer profileId) {
        return profilePassionMatchService.getProfilePassionMatches(profileId);
    }

    @GetMapping("/profileId/{profileId1}/{profileId2}")
    public ResponseEntity<BaseResponse<List<ProfileResponseBodyDto>>> getProfilesByProfileIds(
            @PathVariable Integer profileId1, @PathVariable Integer profileId2)
            throws SQLException, IOException {
        return profileService.getProfilesByProfileIds(profileId1, profileId2);
    }
}
