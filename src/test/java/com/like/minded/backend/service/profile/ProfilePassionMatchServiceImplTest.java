/* LikeMinded (C)2024 */
package com.like.minded.backend.service.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.like.minded.backend.dto.profile.ProfilePassionMatchDto;
import com.like.minded.backend.dto.profile.ProfilePassionMatchListDto;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProfilePassionMatchServiceImplTest {

    @Mock private ProfilePassionRepository profilePassionRepository;

    @InjectMocks private ProfilePassionMatchServiceImpl profilePassionMatchService;

    @Test
    void getProfilePassionMatchesShouldReturnMatches() {
        // Arrange
        Integer profileId = 1;
        List<String> mockMatchList = Arrays.asList("1,2", "3,4");
        when(profilePassionRepository.findProfilePassionMatchesByProfileId(profileId))
                .thenReturn(mockMatchList);

        // Act
        ResponseEntity<BaseResponse<ProfilePassionMatchListDto>> response =
                profilePassionMatchService.getProfilePassionMatches(profileId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BaseResponse<ProfilePassionMatchListDto> body = response.getBody();
        assertNotNull(body);
        assertEquals("Successfully retrieved profile passion match list", body.getMessage());
        List<ProfilePassionMatchDto> matchList = body.getPayload().getMatchList();
        assertEquals(2, matchList.size());
        assertEquals(1, matchList.get(0).getProfileId());
        assertEquals(2, matchList.get(0).getSimilarityScore());
        assertEquals(3, matchList.get(1).getProfileId());
        assertEquals(4, matchList.get(1).getSimilarityScore());
    }

    private void assertNotNull(BaseResponse<ProfilePassionMatchListDto> body) {
        if (body == null) {
            throw new AssertionError("Response body is null");
        }
    }
}
