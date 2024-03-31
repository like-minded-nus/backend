package com.like.minded.backend.service.passion;

import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.repository.passion.PassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PassionServiceImpl implements PassionService {
    private final PassionRepository passionRepository;

    @Override
    public ResponseEntity<BaseResponse<PassionResponse>> getPassions() {

        List<Passion> passionList = passionRepository.findAll();

        PassionResponse passionResponse = new PassionResponse();
        passionResponse.setPassionList(passionList);

        BaseResponse<PassionResponse> response = BaseResponse.<PassionResponse>builder()
                .status(200)
                .message("Successfully retrieved passions")
                .payload(passionResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
