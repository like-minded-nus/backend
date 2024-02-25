package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchService {
    public ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> getProfileMatches(Integer profileId) throws Exception;
    public ResponseEntity<BaseResponse<Match>> createMatchRecord(MatchRequestBodyDto matchRequestBody);
}
