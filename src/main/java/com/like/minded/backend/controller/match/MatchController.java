package com.like.minded.backend.controller.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.service.match.MatchService;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/match")
@CrossOrigin
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("/{profileId}")
    public ResponseEntity<BaseResponse<List<Match>>> getMatchesByProfileId(@PathVariable Integer profileId) {
        return matchService.getMatchesByProfileId(profileId);
    }
}
