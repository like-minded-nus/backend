package com.like.minded.backend.service.vendor;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.VendorException;
import com.like.minded.backend.vo.vendor.VendorResponse;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.repository.vendor.VendorRepository;
import com.like.minded.backend.repository.vendor.ActivityRepository;

import java.util.List;


@Service
public class VendorServiceImpl implements VendorService{
    @Autowired
    private VendorRepository vendorRepository;

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

    @Override
    public ResponseEntity<VendorResponse> updateVendor(Integer vendorId, VendorCreationDto updatedVendorDto) {
        Vendor existingVendor = vendorRepository.findById(vendorId).orElse(null);
        if (existingVendor == null) {
            return ResponseEntity.notFound().build();
        }

        existingVendor.setVendorName(updatedVendorDto.getVendorName());
        existingVendor.setActivityName(updatedVendorDto.getActivityName());
        existingVendor.setAddress(updatedVendorDto.getAddress());
        existingVendor.setPhoneNumber(updatedVendorDto.getPhoneNumber());
        existingVendor.setWebsite(updatedVendorDto.getWebsite());

        try {
            vendorRepository.save(existingVendor);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error updating vendor in the database", e);
        }

        VendorResponse response = VendorResponse.builder()
                .status(200)
                .message("Successfully updated vendor")
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

    @Override
    public ResponseEntity<VendorResponse> deleteVendor(Integer vendorId) {
        Vendor existingVendor = vendorRepository.findById(vendorId).orElse(null);
        if (existingVendor == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            vendorRepository.deleteById(vendorId);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error deleting vendor from the database", e);
        }

        VendorResponse response = VendorResponse.builder()
                .status(200)
                .message("Vendor deleted successfully")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
