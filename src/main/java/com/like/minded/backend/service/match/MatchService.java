/* LikeMinded (C)2024 */
package com.like.minded.backend.service.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MatchService {
    public ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> getProfileMatches(
            Integer profileId) throws Exception;

    public ResponseEntity<BaseResponse<Match>> createMatchRecord(
            MatchRequestBodyDto matchRequestBody);
}
