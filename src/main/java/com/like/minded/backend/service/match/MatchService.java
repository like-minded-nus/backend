package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchService {
    public ResponseEntity<BaseResponse<List<Match>>> getProfileMatches(Integer profileId);
    public ResponseEntity<BaseResponse<Match>> createMatchRecord(Integer userProfileId, Integer targetProfileId);
}
