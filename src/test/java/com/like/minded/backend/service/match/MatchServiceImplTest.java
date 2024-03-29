package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.dto.profile.ProfileDto;
import com.like.minded.backend.repository.match.MatchRepository;
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

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private MatchStrategy matchStrategy;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void getProfileMatchesTest() throws Exception {
        Blob mockBlob = mock(Blob.class);
        byte[] bytes = new byte[0];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        when(mockBlob.getBinaryStream()).thenReturn(byteArrayInputStream);

        Integer profileId = 1;
        Match mockMatch = new Match(1, 1, 2, true, true);
        Profile mockProfile = Profile.builder()
                .profileId(1)
                .userId(1)
                .displayName("Test")
                .gender("male")
                .birthdate(LocalDate.now())
                .bio("bio")
                .image1(mockBlob)
                .image2(mockBlob)
                .image3(mockBlob)
                .image4(mockBlob)
                .image5(mockBlob)
                .image6(mockBlob)
                .build();

        ProfileDto mockProfileDto = new ProfileDto(
                1,
                1,
                "Test",
                "male",
                LocalDate.now(),
                "bio",
                "",
                "",
                "",
                "",
                "",
                ""
        );

        List<Match> matches = List.of(mockMatch);

        when(matchStrategy.findMatches(profileId)).thenReturn(matches);
        when(profileRepository.findById(anyInt())).thenReturn(Optional.of(mockProfile));
        when(modelMapper.map(mockProfile, ProfileDto.class)).thenReturn(mockProfileDto);

        ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> response = matchService.getProfileMatches(profileId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).getPayload().isEmpty());

        verify(matchStrategy).findMatches(profileId);
        verify(profileRepository).findById(anyInt());
        verify(modelMapper).map(mockProfile, ProfileDto.class);
    }

    @Test
    void createMatchRecordTest() {
        MatchRequestBodyDto matchRequestBody = new MatchRequestBodyDto(1, 2, true);
        Match savedMatch = Match.builder()
                .matchId(1)
                .profileId_1(1)
                .profileId_2(2)
                .like_1(true)
                .like_2(false)
                .build();

        when(matchRepository.findMatchByProfileIds(anyInt(), anyInt())).thenReturn(null);
        when(matchRepository.save(any(Match.class))).thenReturn(savedMatch);

        ResponseEntity<BaseResponse<Match>> response = matchService.createMatchRecord(matchRequestBody);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getPayload());

        // Verify save operation was called
        verify(matchRepository).save(any(Match.class));
    }
}