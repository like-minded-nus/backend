package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.match.MatchRepository;
import com.like.minded.backend.vo.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private MatchRepository matchRepository;

    public ResponseEntity<BaseResponse<List<Match>>> getProfileMatches(Integer profileId) {
        List<Match> matches = matchRepository.findProfileMatches(profileId);

        BaseResponse<List<Match>> response = BaseResponse.<List<Match>>builder()
                .status(200)
                .message("Successfully retrieved list of matches")
                .payload(matches)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<BaseResponse<Match>> createMatchRecord(Integer userProfileId, Integer targetProfileId) {
        BaseResponse<Match> response;
        String message;

        // Find match record from db
        Match match = matchRepository.findMatchByProfileIds(targetProfileId, userProfileId);

//        logger.info("before: {}", match);

        // If match record exists, update it
        if (match != null) {
            match.setLike_2(true);
            message = "Successfully updated match record";
        }
        // Otherwise, create new match record
        else {
            match = new Match();
            match.setProfileId_1(userProfileId);
            match.setProfileId_2(targetProfileId);
            match.setLike_1(true);
            message = "Successfully created match record";
        }

//        logger.info("after: {}", match);

        try {
            matchRepository.save(match);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving into Database", e);
        }

        response = BaseResponse.<Match>builder()
                .status(200)
                .message(message)
                .payload(match)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
