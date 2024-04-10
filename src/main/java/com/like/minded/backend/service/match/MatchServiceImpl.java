/* LikeMinded (C)2024 */
package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.dto.profile.ProfileDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.match.MatchRepository;
import com.like.minded.backend.repository.profile.ProfileRepository;
import com.like.minded.backend.utils.BlobUtils;
import com.like.minded.backend.vo.BaseResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final ModelMapper modelMapper;
    private final MatchRepository matchRepository;
    private final ProfileRepository profileRepository;
    private final MatchStrategy matchStrategy;

    public ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> getProfileMatches(
            Integer profileId) throws Exception {
        List<MatchResponseBodyDto> matchResponseBodyDtoList = new ArrayList<>();

        List<Match> matches = matchStrategy.findMatches(profileId);
        for (Match match : matches) {
            int queryId =
                    match.getProfileId_1().equals(profileId)
                            ? match.getProfileId_2()
                            : match.getProfileId_1();
            Optional<Profile> profileOptional = profileRepository.findById(queryId);

            if (profileOptional.isEmpty()) {
                throw new Exception("Profile should not be empty");
            }

            Profile profile = profileOptional.get();
            String image1 = BlobUtils.blobToBase64(profile.getImage1());
            String image2 = BlobUtils.blobToBase64(profile.getImage2());
            String image3 = BlobUtils.blobToBase64(profile.getImage3());
            String image4 = BlobUtils.blobToBase64(profile.getImage4());
            String image5 = BlobUtils.blobToBase64(profile.getImage5());
            String image6 = BlobUtils.blobToBase64(profile.getImage6());

            ProfileDto profileDto = modelMapper.map(profile, ProfileDto.class);
            profileDto.setImage1(image1);
            profileDto.setImage2(image2);
            profileDto.setImage3(image3);
            profileDto.setImage4(image4);
            profileDto.setImage5(image5);
            profileDto.setImage6(image6);

            MatchResponseBodyDto matchResponseBodyDto = new MatchResponseBodyDto(match, profileDto);
            matchResponseBodyDtoList.add(matchResponseBodyDto);
        }

        BaseResponse<List<MatchResponseBodyDto>> response =
                new BaseResponse.Builder<List<MatchResponseBodyDto>>()
                        .status(HttpStatus.OK.value())
                        .message("Successfully retrieved list of matches")
                        .payload(matchResponseBodyDtoList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<BaseResponse<Match>> createMatchRecord(
            MatchRequestBodyDto matchRequestBody) {
        BaseResponse<Match> response;
        String message;

        // Find match record from db
        Match match =
                matchRepository.findMatchByProfileIds(
                        matchRequestBody.getTargetProfileId(), matchRequestBody.getUserProfileId());

        // If match record exists, update it
        if (match != null) {
            if (match.getProfileId_1().equals(matchRequestBody.getUserProfileId()))
                match.setLike_1(matchRequestBody.getLike());
            else if (match.getProfileId_2().equals(matchRequestBody.getUserProfileId()))
                match.setLike_2(matchRequestBody.getLike());
            match.setUpdatedDate(LocalDateTime.now());
            message = "Successfully updated match record";
        }
        // Otherwise, create new match record
        else {
            match = new Match();
            match.setProfileId_1(matchRequestBody.getUserProfileId());
            match.setProfileId_2(matchRequestBody.getTargetProfileId());
            match.setLike_1(matchRequestBody.getLike());
            message = "Successfully created match record";
        }

        try {
            matchRepository.save(match);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving into Database", e);
        }

        response =
                new BaseResponse.Builder<Match>()
                        .status(HttpStatus.OK.value())
                        .message(message)
                        .payload(match)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
