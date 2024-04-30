/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.factory;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;

public interface VendorFactory {
    Vendor createVendor(VendorCreationDto vendorCreationDto);

    default void setCommonVendorProperties(Vendor vendor, VendorCreationDto vendorCreationDto) {
        vendor.setVendorName(vendorCreationDto.getVendorName());
        vendor.setActivityName(vendorCreationDto.getActivityName());
        vendor.setAddress(vendorCreationDto.getAddress());
        vendor.setPhoneNumber(vendorCreationDto.getPhoneNumber());
        vendor.setWebsite(vendorCreationDto.getWebsite());
        vendor.setPassionId(vendorCreationDto.getPassionId());
        vendor.setVendorType(vendorCreationDto.getVendorType());
    }
}
