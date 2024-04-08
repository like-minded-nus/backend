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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.BlobProxy;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ModelMapper modelMapper;
    private final ProfileRepository profileRepository;
    private final ProfilePassionRepository profilePassionRepository;

    @Override
    public ResponseEntity<BaseResponse<ProfileResponseBodyDto>> getProfileByProfileId(Integer id) throws SQLException, IOException {

        Optional<Profile> profileOptional = profileRepository.findById(id);

        if(profileOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        List<String> profilePassions = profilePassionRepository.findProfilePassionNameByProfileId(id);

        Profile profile = profileOptional.get();
        String image1 = profile.getImage1() != null ? BlobUtils.blobToBase64(profile.getImage1()) : null;
        String image2 = profile.getImage2() != null ? BlobUtils.blobToBase64(profile.getImage2()) : null;
        String image3 = profile.getImage3() != null ? BlobUtils.blobToBase64(profile.getImage3()) : null;
        String image4 = profile.getImage4() != null ? BlobUtils.blobToBase64(profile.getImage4()) : null;
        String image5 = profile.getImage5() != null ? BlobUtils.blobToBase64(profile.getImage5()) : null;
        String image6 = profile.getImage6() != null ? BlobUtils.blobToBase64(profile.getImage6()) : null;

        ProfileResponseBodyDto profileDto = modelMapper.map(profile, ProfileResponseBodyDto.class);
        profileDto.setProfilePassionList(profilePassions);
        if (image1 != null) {
            profileDto.setImage1(image1);
        }
        if (image2 != null) {
            profileDto.setImage2(image2);
        }
        if (image3 != null) {
            profileDto.setImage3(image3);
        }
        if (image4 != null) {
            profileDto.setImage4(image4);
        }
        if (image5 != null) {
            profileDto.setImage5(image5);
        }
        if (image6 != null) {
            profileDto.setImage6(image6);
        }

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

        String image1 = profile.getImage1() != null ? BlobUtils.blobToBase64(profile.getImage1()) : null;
        String image2 = profile.getImage2() != null ? BlobUtils.blobToBase64(profile.getImage2()) : null;
        String image3 = profile.getImage3() != null ? BlobUtils.blobToBase64(profile.getImage3()) : null;
        String image4 = profile.getImage4() != null ? BlobUtils.blobToBase64(profile.getImage4()) : null;
        String image5 = profile.getImage5() != null ? BlobUtils.blobToBase64(profile.getImage5()) : null;
        String image6 = profile.getImage6() != null ? BlobUtils.blobToBase64(profile.getImage6()) : null;


        ProfileResponseBodyDto profileDto = modelMapper.map(profile, ProfileResponseBodyDto.class);
        profileDto.setProfilePassionList(profilePassions);
        if (image1 != null) {
            profileDto.setImage1(image1);
        }
        if (image2 != null) {
            profileDto.setImage2(image2);
        }
        if (image3 != null) {
            profileDto.setImage3(image3);
        }
        if (image4 != null) {
            profileDto.setImage4(image4);
        }
        if (image5 != null) {
            profileDto.setImage5(image5);
        }
        if (image6 != null) {
            profileDto.setImage6(image6);
        }

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

        Blob image1Blob = base64ToBlob(userProfileDto.getImage1());
        Blob image2Blob = base64ToBlob(userProfileDto.getImage2());
        Blob image3Blob = base64ToBlob(userProfileDto.getImage3());
        Blob image4Blob = base64ToBlob(userProfileDto.getImage4());
        Blob image5Blob = base64ToBlob(userProfileDto.getImage5());
        Blob image6Blob = base64ToBlob(userProfileDto.getImage6());

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
//        log.info("update profile dto: " + updateUserProfileDto);
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
        updateImageToProfile(updateUserProfileDto.getImage1(), foundProfile::setImage1, profileResponseBodyDto::setImage1);
        updateImageToProfile(updateUserProfileDto.getImage2(), foundProfile::setImage2, profileResponseBodyDto::setImage2);
        updateImageToProfile(updateUserProfileDto.getImage3(), foundProfile::setImage3, profileResponseBodyDto::setImage3);
        updateImageToProfile(updateUserProfileDto.getImage4(), foundProfile::setImage4, profileResponseBodyDto::setImage4);
        updateImageToProfile(updateUserProfileDto.getImage5(), foundProfile::setImage5, profileResponseBodyDto::setImage5);
        updateImageToProfile(updateUserProfileDto.getImage6(), foundProfile::setImage6, profileResponseBodyDto::setImage6);

        try {
            log.info(String.valueOf(foundProfile));
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
                log.error(e.getMessage());
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

    private Blob base64ToBlob(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            String imageBase64 = base64Image.split(",")[1];
            byte[] imageByteArr = Base64.getDecoder().decode(imageBase64.getBytes(StandardCharsets.UTF_8));
            return BlobProxy.generateProxy(imageByteArr);
        }
        return null;
    }

    private void updateImageToProfile(String base64Image, Consumer<Blob> profileSetter, Consumer<String> dtoSetter) {
        if (base64Image != null && !base64Image.isEmpty()) {
            String imageBase64 = base64Image.split(",")[1];
            byte[] imageByteArr = Base64.getDecoder().decode(imageBase64.getBytes(StandardCharsets.UTF_8));
            Blob imageBlob = imageByteArr.length > 0 ? BlobProxy.generateProxy(imageByteArr) : null;
            profileSetter.accept(imageBlob);
            dtoSetter.accept(imageBase64);
        }
    }

}
