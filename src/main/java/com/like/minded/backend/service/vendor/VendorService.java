/* LikeMinded (C)2024 */
package com.like.minded.backend.service.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.vendor.VendorResponseDto;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface VendorService {
    ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto);

    ResponseEntity<VendorResponse> updateVendor(
            Integer vendorId, VendorCreationDto updatedVendorDto);

    Vendor getVendorById(Integer vendorId);

    List<Vendor> getVendorsByPassionIds(List<Integer> passionIds);

    List<VendorResponseDto> getVendorsByPassionIdsAndUserPremiumStatus(
            List<Integer> passionIds, Integer userPremiumStatus);

    List<Vendor> getAllVendors();

    ResponseEntity<VendorResponse> deleteVendor(Integer vendorId);
}
