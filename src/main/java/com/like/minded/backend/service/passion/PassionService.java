/* LikeMinded (C)2024 */
package com.like.minded.backend.service.passion;

import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import org.springframework.http.ResponseEntity;

public interface PassionService {
    public ResponseEntity<BaseResponse<PassionResponse>> getPassions();

    public ResponseEntity<BaseResponse<PassionResponse>> getPassionsByProfileId(Integer profileId);

    public ResponseEntity<BaseResponse<PassionResponse>> getPassionById(Integer passionId);
}
