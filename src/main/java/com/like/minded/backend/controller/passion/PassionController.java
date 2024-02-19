package com.like.minded.backend.controller.passion;

import com.like.minded.backend.service.passion.PassionService;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/passion")
@CrossOrigin(origins = "*")
public class PassionController {

    @Autowired
    private PassionService passionService;

    @GetMapping
    public ResponseEntity<BaseResponse<PassionResponse>> getPassions() {
        return passionService.getPassions();
    }

}

