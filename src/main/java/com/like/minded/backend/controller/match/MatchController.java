/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.service.match.MatchService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/match")
@CrossOrigin(origins = "*")
public class MatchController {
    @Autowired private MatchService matchService;

    @GetMapping("/{profileId}")
    public ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> getProfileMatches(
            @PathVariable Integer profileId) throws Exception {
        return matchService.getProfileMatches(profileId);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Match>> createMatchRecord(
            @RequestBody MatchRequestBodyDto matchRequestBody) {
        return matchService.createMatchRecord(matchRequestBody);
    }
}
