package com.like.minded.backend.service.passion;
import com.like.minded.backend.vo.passion.PassionResponse;
import org.springframework.http.ResponseEntity;


public interface PassionService {
    public ResponseEntity<PassionResponse> getPassions();

}
