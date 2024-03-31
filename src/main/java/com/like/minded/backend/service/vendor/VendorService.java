package com.like.minded.backend.service.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.vo.vendor.VendorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorService {
    ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto);

    ResponseEntity<VendorResponse> updateVendor(Integer vendorId, VendorCreationDto updatedVendorDto);

    Vendor getVendorById(Integer vendorId);

    List<Vendor> getAllVendors();

    ResponseEntity<VendorResponse> deleteVendor(Integer vendorId);
}
