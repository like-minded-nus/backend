package com.like.minded.backend.service.profile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.domain.profile.ProfilePassion;
import com.like.minded.backend.dto.profile.ProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateProfilePassionDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.profile.ProfileRepository;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.utils.BlobUtils;
import com.like.minded.backend.vo.profile.*;
import jakarta.transaction.Transactional;
import org.hibernate.engine.jdbc.BlobProxy;
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
    public ResponseEntity<GetProfileResponse> getProfile(Integer id) throws SQLException, IOException {

        Optional<Profile> profileOptional = profileRepository.findById(id);

        if(profileOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(id);

        Profile profile = profileOptional.get();
        String image1 = BlobUtils.blobToBase64(profile.getImage1());
        String image2 = BlobUtils.blobToBase64(profile.getImage2());
        String image3 = BlobUtils.blobToBase64(profile.getImage3());
        String image4 = BlobUtils.blobToBase64(profile.getImage4());
        String image5 = BlobUtils.blobToBase64(profile.getImage5());
        String image6 = BlobUtils.blobToBase64(profile.getImage6());

        GetProfileResponse response = GetProfileResponse.builder()
                .status(200)
                .message("Successfully retrieved profile")
                .profileId(profile.getProfileId())
                .userId(profile.getUserId())
                .displayName(profile.getDisplayName())
                .gender(profile.getGender())
                .birthdate(profile.getBirthdate())
                .profilePassionList(profilePassions)
                .bio(profile.getBio())
                .image1(image1)
                .image2(image2)
                .image3(image3)
                .image4(image4)
                .image5(image5)
                .image6(image6)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CreateProfileResponse> createProfile(UserProfileDto userProfileDto) {

        String image1Base64 = userProfileDto.getImage1().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image2Base64 = userProfileDto.getImage2().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image3Base64 = userProfileDto.getImage3().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image4Base64 = userProfileDto.getImage4().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image5Base64 = userProfileDto.getImage5().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image6Base64 = userProfileDto.getImage6().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];

        byte[] image1ByteArr = Base64.getDecoder().decode(image1Base64.getBytes(StandardCharsets.UTF_8));
        byte[] image2ByteArr = Base64.getDecoder().decode(image2Base64.getBytes(StandardCharsets.UTF_8));
        byte[] image3ByteArr = Base64.getDecoder().decode(image3Base64.getBytes(StandardCharsets.UTF_8));
        byte[] image4ByteArr = Base64.getDecoder().decode(image4Base64.getBytes(StandardCharsets.UTF_8));
        byte[] image5ByteArr = Base64.getDecoder().decode(image5Base64.getBytes(StandardCharsets.UTF_8));
        byte[] image6ByteArr = Base64.getDecoder().decode(image6Base64.getBytes(StandardCharsets.UTF_8));

        Profile newProfile = Profile.builder()
                .userId(userProfileDto.getUserId())
                .displayName(userProfileDto.getDisplayName())
                .gender(userProfileDto.getGender())
                .birthdate(userProfileDto.getBirthdate())
                .bio(userProfileDto.getBio())
                .image1(BlobProxy.generateProxy(image1ByteArr))
                .image2(BlobProxy.generateProxy(image2ByteArr))
                .image3(BlobProxy.generateProxy(image3ByteArr))
                .image4(BlobProxy.generateProxy(image4ByteArr))
                .image5(BlobProxy.generateProxy(image5ByteArr))
                .image6(BlobProxy.generateProxy(image6ByteArr))
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
