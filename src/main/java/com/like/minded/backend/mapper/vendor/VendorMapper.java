/* LikeMinded (C)2024 */
package com.like.minded.backend.mapper.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorId", ignore = true)
    @Mapping(target = "vendorName", source = "vendorName")
    @Mapping(target = "activityName", source = "activityName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "website", source = "website")
    Vendor mapToVendor(VendorCreationDto vendorCreationDto);
}
