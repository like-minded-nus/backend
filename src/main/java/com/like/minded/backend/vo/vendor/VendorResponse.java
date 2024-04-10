package com.like.minded.backend.vo.vendor;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Builder
@Data
@AllArgsConstructor
public class VendorResponse {
    private Integer status;
    private String message;
}

