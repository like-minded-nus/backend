package com.like.minded.backend.service.profile;

import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.dto.profile.ProfileResponseBodyDto;
import com.like.minded.backend.dto.profile.UpdateUserProfileDto;
import com.like.minded.backend.dto.profile.UserProfileDto;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.repository.profile.ProfileRepository;
import com.like.minded.backend.utils.BlobUtils;
import com.like.minded.backend.vo.BaseResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfilePassionRepository profilePassionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void getProfileByProfileIdShouldReturnProfile() throws Exception {
        // Arrange
        Integer profileId = 1;
        Blob mockBlob = mock(Blob.class);
        byte[] bytes = new byte[0];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        when(mockBlob.getBinaryStream()).thenReturn(byteArrayInputStream);

        Profile mockProfile = new Profile();
        mockProfile.setProfileId(profileId);
        mockProfile.setUserId(1);
        mockProfile.setDisplayName("Test Name");
        mockProfile.setGender("male");
        mockProfile.setBirthdate(LocalDate.now());
        mockProfile.setBio("bio");
        mockProfile.setImage1(mockBlob);
        mockProfile.setImage2(mockBlob);
        mockProfile.setImage3(mockBlob);
        mockProfile.setImage4(mockBlob);
        mockProfile.setImage5(mockBlob);
        mockProfile.setImage6(mockBlob);

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(mockProfile));
        when(profilePassionRepository.findProfilePassionNameByProfileId(profileId)).thenReturn(List.of("Passion1", "Passion2"));
        when(modelMapper.map(any(Profile.class), eq(ProfileResponseBodyDto.class))).thenAnswer(invocation -> {
            Profile source = invocation.getArgument(0);
            ProfileResponseBodyDto dto = new ProfileResponseBodyDto();
            dto.setProfileId(source.getProfileId());
            dto.setUserId(source.getUserId());
            dto.setDisplayName(source.getDisplayName());
            dto.setGender(source.getGender());
            dto.setBirthdate(LocalDate.now());
            dto.setBio(source.getBio());
            dto.setProfilePassionList(List.of("1", "2", "3"));
            dto.setImage1(BlobUtils.blobToBase64(source.getImage1()));
            dto.setImage2(BlobUtils.blobToBase64(source.getImage2()));
            dto.setImage3(BlobUtils.blobToBase64(source.getImage3()));
            dto.setImage4(BlobUtils.blobToBase64(source.getImage4()));
            dto.setImage5(BlobUtils.blobToBase64(source.getImage5()));
            dto.setImage6(BlobUtils.blobToBase64(source.getImage6()));
            return dto;
        });

        // Act
        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> responseEntity = profileService.getProfileByProfileId(profileId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Successfully retrieved profile by profileId", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getPayload().getDisplayName());

        verify(profileRepository).findById(profileId);
        verify(profilePassionRepository).findProfilePassionNameByProfileId(profileId);
    }

    @Test
    void getProfileByUserIdShouldReturnProfile() throws Exception {
        // Arrange
        Integer userId = 1;
        Blob mockBlob = mock(Blob.class);
        byte[] bytes = new byte[0]; // Assuming an empty byte array for simplicity
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        when(mockBlob.getBinaryStream()).thenReturn(byteArrayInputStream);

        Profile mockProfile = new Profile();
        mockProfile.setProfileId(1);
        mockProfile.setUserId(userId);
        mockProfile.setDisplayName("Test Name");
        mockProfile.setGender("male");
        mockProfile.setBirthdate(LocalDate.now());
        mockProfile.setBio("bio");
        mockProfile.setImage1(mockBlob);
        mockProfile.setImage2(mockBlob);
        mockProfile.setImage3(mockBlob);
        mockProfile.setImage4(mockBlob);
        mockProfile.setImage5(mockBlob);
        mockProfile.setImage6(mockBlob);

        when(profileRepository.findByUserId(userId)).thenReturn(mockProfile);
        when(profilePassionRepository.findProfilePassionNameByProfileId(mockProfile.getProfileId())).thenReturn(List.of("Passion1", "Passion2"));
        when(modelMapper.map(any(Profile.class), eq(ProfileResponseBodyDto.class))).thenAnswer(invocation -> {
            Profile source = invocation.getArgument(0);
            ProfileResponseBodyDto dto = new ProfileResponseBodyDto();
            dto.setProfileId(source.getProfileId());
            dto.setUserId(source.getUserId());
            dto.setDisplayName(source.getDisplayName());
            dto.setGender(source.getGender());
            dto.setBirthdate(LocalDate.now());
            dto.setBio(source.getBio());
            dto.setProfilePassionList(List.of("1", "2", "3"));
            dto.setImage1(BlobUtils.blobToBase64(source.getImage1()));
            dto.setImage2(BlobUtils.blobToBase64(source.getImage2()));
            dto.setImage3(BlobUtils.blobToBase64(source.getImage3()));
            dto.setImage4(BlobUtils.blobToBase64(source.getImage4()));
            dto.setImage5(BlobUtils.blobToBase64(source.getImage5()));
            dto.setImage6(BlobUtils.blobToBase64(source.getImage6()));
            return dto;
        });

        // Act
        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response = profileService.getProfileByUserId(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BaseResponse<ProfileResponseBodyDto> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Successfully retrieved profile by userId", responseBody.getMessage());
        ProfileResponseBodyDto dto = responseBody.getPayload();
        assertNotNull(dto);
        assertEquals(userId, dto.getUserId());
        assertEquals("Test Name", dto.getDisplayName());

        verify(profileRepository).findByUserId(userId);
        verify(profilePassionRepository).findProfilePassionNameByProfileId(anyInt());
    }

    @Test
    void createProfileShouldReturnCreatedProfile() {
        // Arrange
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setProfileId(1);
        userProfileDto.setUserId(1);
        userProfileDto.setDisplayName("Test Name");
        userProfileDto.setGender("male");
        userProfileDto.setBirthdate(LocalDate.now());
        userProfileDto.setBio("bio");
        userProfileDto.setProfilePassionList(List.of(1,2,3));
        userProfileDto.setImage1("");
        userProfileDto.setImage2("");
        userProfileDto.setImage3("");
        userProfileDto.setImage4("");
        userProfileDto.setImage5("");
        userProfileDto.setImage6("");

        Profile profile = Profile.builder()
                .userId(1)
                .displayName("Test Name")
                .gender("male")
                .birthdate(LocalDate.now())
                .bio("bio")
                .build();

        when(profileRepository.save(profile)).thenAnswer(invocation -> {
            Profile savedProfile = invocation.getArgument(0);
            if (savedProfile.getProfileId() == null) {
                savedProfile.setProfileId(1);
            }
            return savedProfile;
        });
        when(profilePassionRepository.saveAll(anyIterable())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response = profileService.createProfile(userProfileDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successfully created profile", response.getBody().getMessage());

        verify(profileRepository, times(1)).save(any(Profile.class));
    }

    @Test
    void updateProfileShouldUpdateAndReturnProfile() throws Exception {
        // Arrange
        Integer profileId = 1;
        UpdateUserProfileDto updateUserProfileDto = new UpdateUserProfileDto();
        updateUserProfileDto.setProfileId(profileId);
        updateUserProfileDto.setDisplayName("Test Name");
        updateUserProfileDto.setBio("bio");
        updateUserProfileDto.setProfilePassionList(List.of(1,2,3));

        Profile profile = Profile.builder()
                .userId(1)
                .displayName("Test Name")
                .gender("male")
                .birthdate(LocalDate.now())
                .bio("bio")
                .build();

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenAnswer(invocation -> {
            Profile savedProfile = invocation.getArgument(0);
            if (savedProfile.getProfileId() == null) {
                savedProfile.setProfileId(1);
            }
            return savedProfile;
        });
        when(profilePassionRepository.findProfilePassionNameByProfileId(profileId)).thenReturn(List.of("Passion1", "Passion2"));

        // Act
        ResponseEntity<BaseResponse<ProfileResponseBodyDto>> response = profileService.updateProfile(updateUserProfileDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successfully updated profile", response.getBody().getMessage());
        assertNotNull(response.getBody().getPayload().getDisplayName());
        assertEquals("Test Name", response.getBody().getPayload().getDisplayName());

        verify(profileRepository).findById(profileId);
        verify(profileRepository).save(profile);
    }
}