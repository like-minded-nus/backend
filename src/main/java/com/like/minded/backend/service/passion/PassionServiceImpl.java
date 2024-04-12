/* LikeMinded (C)2024 */
package com.like.minded.backend.service.passion;

import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.repository.passion.PassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        BaseResponse<PassionResponse> response =
                BaseResponse.<PassionResponse>builder()
                        .status(200)
                        .message("Successfully retrieved passions")
                        .payload(passionResponse)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<PassionResponse>> getPassionsByProfileId(Integer profileId) {

        List<Passion> passionList = passionRepository.findPassionsByProfileId(profileId);

        PassionResponse passionResponse = new PassionResponse();
        passionResponse.setPassionList(passionList);

        BaseResponse<PassionResponse> response =
                BaseResponse.<PassionResponse>builder()
                        .status(200)
                        .message("Successfully retrieved passions")
                        .payload(passionResponse)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<PassionResponse>> getPassionById(Integer passionId) {
        Optional<Passion> passionOptional = passionRepository.findById(passionId);
        if (passionOptional.isPresent()) {
            PassionResponse passionResponse = new PassionResponse();
            passionResponse.setPassionList(List.of(passionOptional.get()));
            BaseResponse<PassionResponse> response =
                    BaseResponse.<PassionResponse>builder()
                            .status(200)
                            .message("Successfully retrieved passion")
                            .payload(passionResponse)
                            .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            BaseResponse<PassionResponse> response =
                    BaseResponse.<PassionResponse>builder()
                            .status(404)
                            .message("Passion not found")
                            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
