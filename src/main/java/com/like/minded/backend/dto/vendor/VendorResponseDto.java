/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.vendor;

import com.like.minded.backend.dto.voucher.VoucherResponseDto;
import com.like.minded.backend.enums.VendorType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponseDto {
    private Integer vendorId;
    private String vendorName;
    private String activityName;
    private String address;
    private Integer phoneNumber;
    private String website;
    private Integer passionId;
    private VendorType vendorType;
    private List<VoucherResponseDto> vouchers;
}
