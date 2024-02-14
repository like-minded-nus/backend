package com.like.minded.backend.service.profile;

import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.domain.profile.ProfilePassion;
import com.like.minded.backend.dto.profile.ProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.profile.ProfileRepository;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.vo.profile.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfilePassionRepository profilePassionRepository;

    @Override
    public ResponseEntity<GetProfileResponse> getProfile(Integer id) {

        Optional<Profile> profileOptional = profileRepository.findById(id);

        if(profileOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(id);

        Profile profile = profileOptional.get();

        GetProfileResponse response = GetProfileResponse.builder()
                .status(200)
                .message("Successfully retrieved profile")
                .profileId(profile.getProfileId())
                .userId(profile.getUserId())
                .displayName(profile.getDisplayName())
                .gender(profile.getGender())
                .birthdate(profile.getBirthdate())
                .profilePassionList(profilePassions)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CreateProfileResponse> createProfile(UserProfileDto userProfileDto) {

        Profile newProfile = Profile.builder()
                .userId(userProfileDto.getUserId())
                .displayName(userProfileDto.getDisplayName())
                .gender(userProfileDto.getGender())
                .birthdate(userProfileDto.getBirthdate())
                .build();

        try {
            profileRepository.save(newProfile);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving profile into Database", e);
        }

        List<ProfilePassion> profilePassionList = new ArrayList<>();

        for( Integer passionId : userProfileDto.getProfilePassionList()){
            ProfilePassion profilePassion = new ProfilePassion();
            profilePassion.setProfileId(newProfile.getProfileId());
            profilePassion.setPassionId(passionId);
            profilePassionList.add(profilePassion);
        }

        try {
            profilePassionRepository.saveAll(profilePassionList);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error creating profile passions into Database", e);
        }

        CreateProfileResponse response = CreateProfileResponse.builder()
                .status(200)
                .message("Successfully created profile")
                .profileId(newProfile.getProfileId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetProfileResponse> updateProfile(UpdateUserProfileDto updateUserProfileDto) {
        Optional<Profile> profile = profileRepository.findById(updateUserProfileDto.getProfileId());
        if(profile.isEmpty()){
            return null;
        }

        Profile foundProfile = profile.get();
        foundProfile.setDisplayName(updateUserProfileDto.getDisplayName());

        profileRepository.save(foundProfile);

        GetProfileResponse response = GetProfileResponse.builder()
                .status(200)
                .message("Successfully updated profile")
                .profileId(foundProfile.getProfileId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<CreateProfilePassionResponse> createProfilePassions(ProfilePassionDto profilePassionDto) {

        try {
            profilePassionRepository.saveAll(profilePassionDto.getProfilePassionList());
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error creating profile passions into Database", e);
        }

        CreateProfilePassionResponse response = CreateProfilePassionResponse.builder()
                .status(200)
                .message("Successfully created profile passions")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<GetProfilePassionResponse> getProfilePassions(Integer profileId) {
        {

            List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(profileId);

            GetProfilePassionResponse response = GetProfilePassionResponse.builder()
                    .status(200)
                    .message("Successfully retrieved profile passions")
                    .profilePassionList(profilePassions)
                    .build();


            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateProfilePassionResponse> updateProfilePassions(UpdateProfilePassionDto updateProfilePassionDto) {

        try {
            profilePassionRepository.deleteProfilePassionByProfileId(updateProfilePassionDto.getProfileId());
            profilePassionRepository.saveAll(updateProfilePassionDto.getProfilePassionList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseTransactionException("Error updating profile passions into Database", e);

        }

        UpdateProfilePassionResponse response = UpdateProfilePassionResponse.builder()
                .status(200)
                .message("Successfully updated profile passions")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }
}
