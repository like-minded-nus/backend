/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.dto.match.MatchRequestBodyDto;
import com.like.minded.backend.dto.match.MatchResponseBodyDto;
import com.like.minded.backend.service.match.MatchService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class MatchControllerTest {

    @Mock MatchService matchService;

    @InjectMocks MatchController matchController;

    @Test
    void getProfileMatches() throws Exception {
        Integer profileId = 1;
        BaseResponse<List<MatchResponseBodyDto>> response =
                BaseResponse.<List<MatchResponseBodyDto>>builder().build();
        when(matchService.getProfileMatches(profileId))
                .thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<List<MatchResponseBodyDto>>> result =
                matchController.getProfileMatches(profileId);
        assertEquals(OK, result.getStatusCode());
    }

    @Test
    void createMatchRecord() {
        MatchRequestBodyDto matchRequestBody = new MatchRequestBodyDto();
        BaseResponse<Match> response = BaseResponse.<Match>builder().build();
        when(matchService.createMatchRecord(matchRequestBody))
                .thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<Match>> result =
                matchController.createMatchRecord(matchRequestBody);
        assertEquals(OK, result.getStatusCode());
    }
}
