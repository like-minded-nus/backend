/* LikeMinded (C)2024 */
package com.like.minded.backend.service.profile;

import com.like.minded.backend.dto.profile.ProfilePassionMatchDto;
import com.like.minded.backend.dto.profile.ProfilePassionMatchListDto;
import com.like.minded.backend.repository.profile.ProfilePassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfilePassionMatchServiceImpl implements ProfilePassionMatchService {

    ProfilePassionRepository profilePassionRepository;

    @Override
    public ResponseEntity<BaseResponse<ProfilePassionMatchListDto>> getProfilePassionMatches(
            Integer profileId) {
        {
            List<String> profileMatchList =
                    profilePassionRepository.findProfilePassionMatchesByProfileId(profileId);

            List<ProfilePassionMatchDto> matchList = new ArrayList<>();
            ProfilePassionMatchListDto profilePassionMatchListDto =
                    new ProfilePassionMatchListDto();
            profilePassionMatchListDto.setProfileId(profileId);
            for (String s : profileMatchList) {
                String[] pair = s.split(",");
                matchList.add(
                        new ProfilePassionMatchDto(
                                Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
            }
            profilePassionMatchListDto.setMatchList(matchList);

            BaseResponse<ProfilePassionMatchListDto> response =
                    new BaseResponse.Builder<ProfilePassionMatchListDto>()
                            .status(HttpStatus.OK.value())
                            .message("Successfully retrieved profile passion match list")
                            .payload(profilePassionMatchListDto)
                            .build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
