/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.voucher;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherCreationDto {
    private String voucherName;
    private LocalDate voucherEndDate;
    //    private String voucherDescription;
    private Integer voucherType;
    private Integer voucherAmount;
    private boolean redeemStatus;
    private Integer vendorId;
}
