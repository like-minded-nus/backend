package com.like.minded.backend.vo.voucher;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoucherResponse {
    private Integer status;
    private String message;
}
