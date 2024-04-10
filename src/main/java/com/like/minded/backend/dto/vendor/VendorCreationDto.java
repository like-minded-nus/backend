package com.like.minded.backend.dto.vendor;

import com.like.minded.backend.enums.VendorType;
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
    private VendorType vendorType;
    private String intensityLevel;
    private String conversationFriendly;

}
