/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.voucher;

import com.like.minded.backend.domain.voucher.VoucherType;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherResponseDto {
    private Integer voucherId;
    private String voucherName;
    private VoucherType voucherType;
    private Integer voucherAmount;
    private LocalDate voucherEndDate;
    private boolean redeemStatus;
    private Integer vendorId;
}
