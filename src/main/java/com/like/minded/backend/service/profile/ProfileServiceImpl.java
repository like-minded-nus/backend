package com.like.minded.backend.service.profile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.domain.profile.ProfilePassion;
import com.like.minded.backend.dto.profile.*;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.profile.ProfileRepository;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.utils.BlobUtils;
import com.like.minded.backend.vo.BaseResponse;
import jakarta.transaction.Transactional;
import org.hibernate.engine.jdbc.BlobProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfilePassionRepository profilePassionRepository;

    @Override
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByProfileId(Integer id) throws SQLException, IOException {

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

        ProfileResponseBodyDto profileDto = new ProfileResponseBodyDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setUserId(profile.getUserId());
        profileDto.setDisplayName(profile.getDisplayName());
        profileDto.setGender(profile.getGender());
        profileDto.setBirthdate(profile.getBirthdate());
        profileDto.setBio(profile.getBio());
        profileDto.setProfilePassionList(profilePassions);
        profileDto.setImage1(image1);
        profileDto.setImage2(image2);
        profileDto.setImage3(image3);
        profileDto.setImage4(image4);
        profileDto.setImage5(image5);
        profileDto.setImage6(image6);

        BaseResponse<ProfileResponseBodyDto> response = BaseResponse.<ProfileResponseBodyDto>builder()
                .status(200)
                .message("Successfully retrieved profile by profileId")
                .payload(profileDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByUserId(Integer userId) throws SQLException, IOException {

        Optional<Profile> profileOptional = Optional.ofNullable(profileRepository.findByUserId(userId));

        if(profileOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        Profile profile = profileOptional.get();

        List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(profile.getProfileId());

        String image1 = BlobUtils.blobToBase64(profile.getImage1());
        String image2 = BlobUtils.blobToBase64(profile.getImage2());
        String image3 = BlobUtils.blobToBase64(profile.getImage3());
        String image4 = BlobUtils.blobToBase64(profile.getImage4());
        String image5 = BlobUtils.blobToBase64(profile.getImage5());
        String image6 = BlobUtils.blobToBase64(profile.getImage6());

        ProfileResponseBodyDto profileDto = new ProfileResponseBodyDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setUserId(profile.getUserId());
        profileDto.setDisplayName(profile.getDisplayName());
        profileDto.setGender(profile.getGender());
        profileDto.setBirthdate(profile.getBirthdate());
        profileDto.setBio(profile.getBio());
        profileDto.setProfilePassionList(profilePassions);
        profileDto.setImage1(image1);
        profileDto.setImage2(image2);
        profileDto.setImage3(image3);
        profileDto.setImage4(image4);
        profileDto.setImage5(image5);
        profileDto.setImage6(image6);

        BaseResponse<ProfileResponseBodyDto> response = BaseResponse.<ProfileResponseBodyDto>builder()
                .status(200)
                .message("Successfully retrieved profile by userId")
                .payload(profileDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> createProfile(UserProfileDto userProfileDto) {

        String image1Base64 = userProfileDto.getImage1().isEmpty() ? "" : userProfileDto.getImage1().split(",")[1];
        String image2Base64 = userProfileDto.getImage2().isEmpty() ? "" : userProfileDto.getImage2().split(",")[1];
        String image3Base64 = userProfileDto.getImage3().isEmpty() ? "" : userProfileDto.getImage3().split(",")[1];
        String image4Base64 = userProfileDto.getImage4().isEmpty() ? "" : userProfileDto.getImage4().split(",")[1];
        String image5Base64 = userProfileDto.getImage5().isEmpty() ? "" : userProfileDto.getImage5().split(",")[1];
        String image6Base64 = userProfileDto.getImage6().isEmpty() ? "" : userProfileDto.getImage6().split(",")[1];

        byte[] image1ByteArr = !image1Base64.isEmpty() ? Base64.getDecoder().decode(image1Base64.getBytes(StandardCharsets.UTF_8)) : null;
        byte[] image2ByteArr = !image2Base64.isEmpty() ? Base64.getDecoder().decode(image2Base64.getBytes(StandardCharsets.UTF_8)) : null;
        byte[] image3ByteArr = !image3Base64.isEmpty() ? Base64.getDecoder().decode(image3Base64.getBytes(StandardCharsets.UTF_8)) : null;
        byte[] image4ByteArr = !image4Base64.isEmpty() ? Base64.getDecoder().decode(image4Base64.getBytes(StandardCharsets.UTF_8)) : null;
        byte[] image5ByteArr = !image5Base64.isEmpty() ? Base64.getDecoder().decode(image5Base64.getBytes(StandardCharsets.UTF_8)) : null;
        byte[] image6ByteArr = !image6Base64.isEmpty() ? Base64.getDecoder().decode(image6Base64.getBytes(StandardCharsets.UTF_8)) : null;

        Blob image1Blob = image1ByteArr != null ? BlobProxy.generateProxy(image1ByteArr) : null;
        Blob image2Blob = image2ByteArr != null ? BlobProxy.generateProxy(image2ByteArr) : null;
        Blob image3Blob = image3ByteArr != null ? BlobProxy.generateProxy(image3ByteArr) : null;
        Blob image4Blob = image4ByteArr != null ? BlobProxy.generateProxy(image4ByteArr) : null;
        Blob image5Blob = image5ByteArr != null ? BlobProxy.generateProxy(image5ByteArr) : null;
        Blob image6Blob = image6ByteArr != null ? BlobProxy.generateProxy(image6ByteArr) : null;

        Profile newProfile = Profile.builder()
                .userId(userProfileDto.getUserId())
                .displayName(userProfileDto.getDisplayName())
                .gender(userProfileDto.getGender())
                .birthdate(userProfileDto.getBirthdate())
                .bio(userProfileDto.getBio())
                .image1(image1Blob)
                .image2(image2Blob)
                .image3(image3Blob)
                .image4(image4Blob)
                .image5(image5Blob)
                .image6(image6Blob)
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

        List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(newProfile.getProfileId());

        ProfileResponseBodyDto profileResponseBodyDto = new ProfileResponseBodyDto();
        profileResponseBodyDto.setProfileId(newProfile.getProfileId());
        profileResponseBodyDto.setUserId(newProfile.getUserId());
        profileResponseBodyDto.setDisplayName(newProfile.getDisplayName());
        profileResponseBodyDto.setGender(newProfile.getGender());
        profileResponseBodyDto.setBirthdate(newProfile.getBirthdate());
        profileResponseBodyDto.setBio(newProfile.getBio());
        profileResponseBodyDto.setProfilePassionList(profilePassions);
        profileResponseBodyDto.setImage1(image1Base64);
        profileResponseBodyDto.setImage2(image2Base64);
        profileResponseBodyDto.setImage3(image3Base64);
        profileResponseBodyDto.setImage4(image4Base64);
        profileResponseBodyDto.setImage5(image5Base64);
        profileResponseBodyDto.setImage6(image6Base64);

        BaseResponse <ProfileResponseBodyDto> response = BaseResponse.<ProfileResponseBodyDto>builder()
                .status(200)
                .message("Successfully created profile")
                .payload(profileResponseBodyDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> updateProfile(UpdateUserProfileDto updateUserProfileDto) {
        Optional<Profile> profile = profileRepository.findById(updateUserProfileDto.getProfileId());
        ProfileResponseBodyDto profileResponseBodyDto = new ProfileResponseBodyDto();

        if(profile.isEmpty()){
            return null;
        }

        Profile foundProfile = profile.get();

        if(updateUserProfileDto.getDisplayName() != null){
            foundProfile.setDisplayName(updateUserProfileDto.getDisplayName());
            profileResponseBodyDto.setDisplayName(updateUserProfileDto.getDisplayName());
        }

        if(updateUserProfileDto.getBio() != null){
            foundProfile.setBio(updateUserProfileDto.getBio());
            profileResponseBodyDto.setBio(updateUserProfileDto.getBio());
        }

        if(updateUserProfileDto.getImage1() != null){
            String image1Base64 = updateUserProfileDto.getImage1().split(",")[1];
            byte[] image1ByteArr = !image1Base64.isEmpty() ? Base64.getDecoder().decode(image1Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image1Blob = image1ByteArr != null ? BlobProxy.generateProxy(image1ByteArr) : null;
            foundProfile.setImage1(image1Blob);
            profileResponseBodyDto.setImage1(image1Base64);
        }

        if(updateUserProfileDto.getImage2() != null){
            String image2Base64 = updateUserProfileDto.getImage2().split(",")[1];
            byte[] image2ByteArr = !image2Base64.isEmpty() ? Base64.getDecoder().decode(image2Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image2Blob = image2ByteArr != null ? BlobProxy.generateProxy(image2ByteArr) : null;
            foundProfile.setImage2(image2Blob);
            profileResponseBodyDto.setImage2(image2Base64);
        }

        if(updateUserProfileDto.getImage3() != null){
            String image3Base64 = updateUserProfileDto.getImage3().split(",")[1];
            byte[] image3ByteArr = !image3Base64.isEmpty() ? Base64.getDecoder().decode(image3Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image3Blob = image3ByteArr != null ? BlobProxy.generateProxy(image3ByteArr) : null;
            foundProfile.setImage3(image3Blob);
            profileResponseBodyDto.setImage3(image3Base64);
        }

        if(updateUserProfileDto.getImage4() != null){
            String image4Base64 = updateUserProfileDto.getImage4().split(",")[1];
            byte[] image4ByteArr = !image4Base64.isEmpty() ? Base64.getDecoder().decode(image4Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image4Blob = image4ByteArr != null ? BlobProxy.generateProxy(image4ByteArr) : null;
            foundProfile.setImage4(image4Blob);
            profileResponseBodyDto.setImage4(image4Base64);
        }

        if(updateUserProfileDto.getImage5() != null){
            String image5Base64 = updateUserProfileDto.getImage5().split(",")[1];
            byte[] image5ByteArr = !image5Base64.isEmpty() ? Base64.getDecoder().decode(image5Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image5Blob = image5ByteArr != null ? BlobProxy.generateProxy(image5ByteArr) : null;
            foundProfile.setImage5(image5Blob);
            profileResponseBodyDto.setImage5(image5Base64);
        }

        if(updateUserProfileDto.getImage6() != null){
            String image6Base64 = updateUserProfileDto.getImage6().split(",")[1];
            byte[] image6ByteArr = !image6Base64.isEmpty() ? Base64.getDecoder().decode(image6Base64.getBytes(StandardCharsets.UTF_8)) : null;
            Blob image6Blob = image6ByteArr != null ? BlobProxy.generateProxy(image6ByteArr) : null;
            foundProfile.setImage6(image6Blob);
            profileResponseBodyDto.setImage6(image6Base64);
        }

        try {
            profileRepository.save(foundProfile);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving profile into Database", e);
        }

        List<ProfilePassion> profilePassionList = new ArrayList<>();

        if(updateUserProfileDto.getProfilePassionList() != null){
            for( Integer passionId : updateUserProfileDto.getProfilePassionList()){
                ProfilePassion profilePassion = new ProfilePassion();
                profilePassion.setProfileId(foundProfile.getProfileId());
                profilePassion.setPassionId(passionId);
                profilePassionList.add(profilePassion);
            }

            try {
                profilePassionRepository.deleteProfilePassionByProfileId(foundProfile.getProfileId());
                profilePassionRepository.saveAll(profilePassionList);
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new DatabaseTransactionException("Error updating profile passions into Database", e);
            }

            List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(foundProfile.getProfileId());
            profileResponseBodyDto.setProfilePassionList(profilePassions);
        }

        profileResponseBodyDto.setProfileId(foundProfile.getProfileId());
        profileResponseBodyDto.setUserId(foundProfile.getUserId());

        BaseResponse<ProfileResponseBodyDto> response = BaseResponse.<ProfileResponseBodyDto>builder()
                .status(200)
                .message("Successfully updated profile")
                .payload(profileResponseBodyDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
