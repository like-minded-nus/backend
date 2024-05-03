/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.creator;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;

public abstract class VendorCreator {

    public abstract Vendor createVendor(VendorCreationDto vendorCreationDto);

    protected void setCommonVendorProperties(Vendor vendor, VendorCreationDto vendorCreationDto) {
        vendor.setVendorName(vendorCreationDto.getVendorName());
        vendor.setActivityName(vendorCreationDto.getActivityName());
        vendor.setAddress(vendorCreationDto.getAddress());
        vendor.setPhoneNumber(vendorCreationDto.getPhoneNumber());
        vendor.setWebsite(vendorCreationDto.getWebsite());
        vendor.setPassionId(vendorCreationDto.getPassionId());
        vendor.setVendorType(vendorCreationDto.getVendorType());
    }
}
