/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.passion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

import com.like.minded.backend.service.passion.PassionService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PassionControllerTest {

    @Mock PassionService passionService;

    @InjectMocks PassionController passionController;

    @Test
    void getPassions() {
        BaseResponse<PassionResponse> response = BaseResponse.<PassionResponse>builder().build();
        when(passionService.getPassions()).thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<PassionResponse>> result = passionController.getPassions();
        assertEquals(OK, result.getStatusCode());
    }

    @Test
    void getPassionsFromProfileId() {
        Integer profileId = 1;
        BaseResponse<PassionResponse> response = BaseResponse.<PassionResponse>builder().build();
        when(passionService.getPassionsByProfileId(profileId))
                .thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<PassionResponse>> result =
                passionController.getPassionsFromProfileId(profileId);
        assertEquals(OK, result.getStatusCode());
    }

    @Test
    void getPassionById() {
        Integer passionId = 1;
        BaseResponse<PassionResponse> response = BaseResponse.<PassionResponse>builder().build();
        when(passionService.getPassionById(passionId))
                .thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<PassionResponse>> result =
                passionController.getPassionById(passionId);
        assertEquals(OK, result.getStatusCode());
    }

    @Test
    void getPassionIdsByName() {
        List<String> passionNames = List.of("Music", "Art");
        BaseResponse<List<Integer>> response = BaseResponse.<List<Integer>>builder().build();
        when(passionService.getPassionIdsByName(passionNames))
                .thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<List<Integer>>> result =
                passionController.getPassionIdsByName(passionNames);
        assertEquals(OK, result.getStatusCode());
    }
}
