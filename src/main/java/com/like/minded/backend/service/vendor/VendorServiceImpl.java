/* LikeMinded (C)2024 */
package com.like.minded.backend.service.vendor;

import com.like.minded.backend.domain.vendor.IndoorVendor;
import com.like.minded.backend.domain.vendor.OutdoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.vendor.creator.IndoorVendorCreator;
import com.like.minded.backend.domain.vendor.creator.OutdoorVendorCreator;
import com.like.minded.backend.domain.vendor.creator.VendorCreator;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.vendor.VendorResponseDto;
import com.like.minded.backend.dto.voucher.VoucherResponseDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.VendorException;
import com.like.minded.backend.repository.vendor.VendorRepository;
import com.like.minded.backend.service.user.NormalUserStrategy;
import com.like.minded.backend.service.user.PremiumUserStrategy;
import com.like.minded.backend.service.user.UserContext;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VendorServiceImpl implements VendorService {

    VendorRepository vendorRepository;
    IndoorVendorCreator indoorVendorCreator;
    OutdoorVendorCreator outdoorVendorCreator;

    public ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto) {
        validateVendorCreationData(vendorCreationDto);

        VendorCreator vendorCreator;

        switch (vendorCreationDto.getVendorType()) {
            case INDOOR:
                vendorCreator = indoorVendorCreator;
                break;
            case OUTDOOR:
                vendorCreator = outdoorVendorCreator;
                break;
            default:
                throw new IllegalArgumentException("Invalid vendor type");
        }

        Vendor vendor = vendorCreator.createVendor(vendorCreationDto);
        vendorRepository.save(vendor);

        VendorResponse response =
                VendorResponse.builder().status(200).message("Successfully created vendor").build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void setCommonVendorProperties(Vendor vendor, VendorCreationDto vendorCreationDto) {
        vendor.setVendorName(vendorCreationDto.getVendorName());
        vendor.setActivityName(vendorCreationDto.getActivityName());
        vendor.setAddress(vendorCreationDto.getAddress());
        vendor.setPhoneNumber(vendorCreationDto.getPhoneNumber());
        vendor.setWebsite(vendorCreationDto.getWebsite());
        vendor.setPassionId(vendorCreationDto.getPassionId());
        vendor.setVendorType(vendorCreationDto.getVendorType());
    }

    @Override
    public ResponseEntity<VendorResponse> updateVendor(
            Integer vendorId, VendorCreationDto updatedVendorDto) {
        Vendor existingVendor = vendorRepository.findById(vendorId).orElse(null);
        if (existingVendor == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingVendor.getVendorType().equals(updatedVendorDto.getVendorType())) {
            return ResponseEntity.badRequest()
                    .body(new VendorResponse(400, "Cannot change vendor type"));
        }

        existingVendor.setVendorName(updatedVendorDto.getVendorName());
        existingVendor.setActivityName(updatedVendorDto.getActivityName());
        existingVendor.setAddress(updatedVendorDto.getAddress());
        existingVendor.setPhoneNumber(updatedVendorDto.getPhoneNumber());
        existingVendor.setWebsite(updatedVendorDto.getWebsite());
        existingVendor.setPassionId(updatedVendorDto.getPassionId());

        if (existingVendor instanceof IndoorVendor
                && updatedVendorDto.getConversationFriendly() != null) {
            ((IndoorVendor) existingVendor)
                    .setConversationFriendly(updatedVendorDto.getConversationFriendly());
        }
        if (existingVendor instanceof OutdoorVendor
                && updatedVendorDto.getIntensityLevel() != null) {
            ((OutdoorVendor) existingVendor)
                    .setIntensityLevel(updatedVendorDto.getIntensityLevel());
        }

        try {
            vendorRepository.save(existingVendor);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error updating vendor in the database", e);
        }

        VendorResponse response =
                VendorResponse.builder().status(200).message("Successfully updated vendor").build();
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
    public List<Vendor> getVendorsByPassionIds(List<Integer> passionIds) {
        return vendorRepository.findAll().stream()
                .filter(vendor -> passionIds.contains(vendor.getPassionId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorResponseDto> getVendorsByPassionIdsAndUserPremiumStatus(
            List<Integer> passionIds, Integer userPremiumStatus) {
        UserContext userContext;
        if (userPremiumStatus.equals(0)) {
            userContext = new UserContext(new NormalUserStrategy());
        } else {
            userContext = new UserContext(new PremiumUserStrategy());
        }
        List<VendorResponseDto> vendorListResponseDto = new ArrayList<>();

        List<Vendor> foundVendorList =
                vendorRepository.findAll().stream()
                        .filter(vendor -> passionIds.contains(vendor.getPassionId()))
                        .collect(Collectors.toList());

        for (Vendor foundVendor : foundVendorList) {
            List<VoucherResponseDto> voucherListResponseDto = new ArrayList<>();
            VendorResponseDto vendorResponseDto =
                    VendorResponseDto.builder()
                            .vendorId(foundVendor.getVendorId())
                            .vendorName(foundVendor.getVendorName())
                            .vendorType(foundVendor.getVendorType())
                            .activityName(foundVendor.getActivityName())
                            .address(foundVendor.getAddress())
                            .phoneNumber(foundVendor.getPhoneNumber())
                            .website(foundVendor.getWebsite())
                            .passionId(foundVendor.getPassionId())
                            .build();
            for (Voucher existingVoucher : foundVendor.getVouchers()) {
                Integer discountedAmount =
                        userContext.performCalculateDiscount(existingVoucher.getVoucherAmount());
                VoucherResponseDto voucherResponseDto =
                        VoucherResponseDto.builder()
                                .voucherId(existingVoucher.getVoucherId())
                                .voucherName(existingVoucher.getVoucherName())
                                .voucherType(existingVoucher.getVoucherType())
                                .voucherAmount(discountedAmount)
                                .voucherEndDate(existingVoucher.getVoucherEndDate())
                                .redeemStatus(existingVoucher.isRedeemStatus())
                                .vendorId(existingVoucher.getVendorId())
                                .build();
                voucherListResponseDto.add(voucherResponseDto);
            }
            vendorResponseDto.setVouchers(voucherListResponseDto);
            vendorListResponseDto.add(vendorResponseDto);
        }
        return vendorListResponseDto;
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

        VendorResponse response =
                VendorResponse.builder().status(200).message("Vendor deleted successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
