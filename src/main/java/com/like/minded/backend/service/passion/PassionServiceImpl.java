package com.like.minded.backend.service.passion;

import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.repository.passion.PassionRepository;
import com.like.minded.backend.vo.passion.PassionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassionServiceImpl implements PassionService{
    @Autowired
    private PassionRepository passionRepository;

    @Override
    public ResponseEntity<PassionResponse> getPassions() {

        List<Passion> passionList = passionRepository.findAll();

        PassionResponse response = PassionResponse.builder()
                .status(200)
                .message("Successfully retrieved all passions")
                .passionList(passionList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
