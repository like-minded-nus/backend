package com.like.minded.backend.service.vendor;

import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.VendorException;
import com.like.minded.backend.vo.vendor.VendorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.repository.vendor.VendorRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;

    @Override
    public ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto) {
        validateVendorCreationData(vendorCreationDto);

        Vendor newVendor = Vendor.builder()
                .vendorName(vendorCreationDto.getVendorName())
                .activityName(vendorCreationDto.getActivityName())
                .address(vendorCreationDto.getAddress())
                .phoneNumber(vendorCreationDto.getPhoneNumber())
                .website(vendorCreationDto.getWebsite())
                .build();

        try {
            vendorRepository.save(newVendor);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving vendor into Database", e);
        }

        VendorResponse response = VendorResponse.builder()
                .status(200)
                .message("Successfully created vendor")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateVendorCreationData(VendorCreationDto vendorCreationDto) {
        if (vendorRepository.existsByVendorName(vendorCreationDto.getVendorName())) {
            throw new VendorException("Vendor name already exists.");
        }

        // Check if the phone number already exists
        if (vendorRepository.existsByPhoneNumber(vendorCreationDto.getPhoneNumber())) {
            throw new VendorException("Vendor with the given phone number already exists.");
        }
    }

    @Override
    public Vendor getVendorById(Integer vendorId) {
        return vendorRepository.findById(vendorId).orElse(null);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
