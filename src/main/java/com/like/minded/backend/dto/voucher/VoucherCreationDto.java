package com.like.minded.backend.dto.voucher;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.voucher.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherCreationDto {
    private String voucherName;
    private LocalDate voucherEndDate;
    private String voucherDescription;
    private boolean redeemStatus;
    private Integer vendorId;
}
