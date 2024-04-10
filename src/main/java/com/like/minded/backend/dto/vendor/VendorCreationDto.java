/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.vendor;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorCreationDto {
    private String vendorName;
    private String activityName;
    private String address;
    private Integer phoneNumber;
    private String website;
    private Integer passionId;
}
