/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.creator;

import com.like.minded.backend.domain.vendor.IndoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import org.springframework.stereotype.Component;

@Component
public class IndoorVendorCreator extends VendorCreator {
    @Override
    public Vendor createVendor(VendorCreationDto vendorCreationDto) {
        IndoorVendor vendor = new IndoorVendor();
        vendor.setConversationFriendly(vendorCreationDto.getConversationFriendly());
        setCommonVendorProperties(vendor, vendorCreationDto);
        return vendor;
    }
}
