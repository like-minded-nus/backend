package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.repository.match.MatchRepository;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    private MatchRepository matchRepository;

    public ResponseEntity<BaseResponse<List<Match>>> getMatchesByProfileId(Integer profileId) {
        List<Match> matches = matchRepository.findMatchesByProfileId(profileId);

        BaseResponse<List<Match>> response = BaseResponse.<List<Match>>builder()
                .status(200)
                .message("Successfully retrieved list of matches")
                .payload(matches)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
