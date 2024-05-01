/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.factory;

import com.like.minded.backend.domain.vendor.OutdoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import org.springframework.stereotype.Component;

@Component
public class OutdoorVendorFactory implements VendorFactory {
    @Override
    public Vendor createVendor(VendorCreationDto vendorCreationDto) {
        OutdoorVendor outdoorVendor = new OutdoorVendor();
        outdoorVendor.setIntensityLevel(vendorCreationDto.getIntensityLevel());
        setCommonVendorProperties(outdoorVendor, vendorCreationDto);
        return outdoorVendor;
    }
}
