/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor.factory;

import com.like.minded.backend.domain.vendor.IndoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import org.springframework.stereotype.Component;

@Component
public class IndoorVendorFactory implements VendorFactory {
    @Override
    public Vendor createVendor(VendorCreationDto vendorCreationDto) {
        IndoorVendor indoorVendor = new IndoorVendor();
        indoorVendor.setConversationFriendly(vendorCreationDto.getConversationFriendly());
        setCommonVendorProperties(indoorVendor, vendorCreationDto);
        return indoorVendor;
    }
}
