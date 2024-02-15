package com.like.minded.backend.controller.profile;

import com.like.minded.backend.dto.profile.ProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.service.profile.ProfileService;
import com.like.minded.backend.vo.profile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable Integer id) throws SQLException, IOException {
        return profileService.getProfile(id);
    }

    @PostMapping
    public ResponseEntity<CreateProfileResponse> createProfile(@RequestBody UserProfileDto userProfileDto) {
        return profileService.createProfile(userProfileDto);
    }

    @PutMapping
    public ResponseEntity<GetProfileResponse> updateProfile(@RequestBody UpdateUserProfileDto updateUserProfileDto) {
        return profileService.updateProfile(updateUserProfileDto);
    }

    @PostMapping("/passions")
    public ResponseEntity<CreateProfilePassionResponse> createProfilePassions(@RequestBody ProfilePassionDto profilePassionDto) {
        return profileService.createProfilePassions(profilePassionDto);
    }

    @GetMapping("/passions/{profileId}")
    public ResponseEntity<GetProfilePassionResponse> getUserPassions(@PathVariable Integer profileId) {
        return profileService.getProfilePassions(profileId);
    }

    @PutMapping("/passions")
    public ResponseEntity<UpdateProfilePassionResponse> updateProfilePassions(@RequestBody UpdateProfilePassionDto updateProfilePassionDto) {
        return profileService.updateProfilePassions(updateProfilePassionDto);
    }

}

