/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.creator;

import com.like.minded.backend.domain.vendor.OutdoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import org.springframework.stereotype.Component;

@Component
public class OutdoorVendorCreator extends VendorCreator {
    @Override
    public Vendor createVendor(VendorCreationDto vendorCreationDto) {
        OutdoorVendor vendor = new OutdoorVendor();
        vendor.setIntensityLevel(vendorCreationDto.getIntensityLevel());
        setCommonVendorProperties(vendor, vendorCreationDto);
        return vendor;
    }
}
