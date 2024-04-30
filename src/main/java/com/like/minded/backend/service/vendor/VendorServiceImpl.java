/* LikeMinded (C)2024 */
package com.like.minded.backend.service.vendor;

import com.like.minded.backend.domain.vendor.IndoorVendor;
import com.like.minded.backend.domain.vendor.OutdoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.vendor.factory.IndoorVendorFactory;
import com.like.minded.backend.domain.vendor.factory.OutdoorVendorFactory;
import com.like.minded.backend.domain.vendor.factory.VendorFactory;
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

    private final VendorRepository vendorRepository;
    private final IndoorVendorFactory indoorVendorFactory;
    private final OutdoorVendorFactory outdoorVendorFactory;

    @Override
    public ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto) {
        validateVendorCreationData(vendorCreationDto);

        VendorFactory vendorFactory;
        switch (vendorCreationDto.getVendorType()) {
            case INDOOR:
                vendorFactory = indoorVendorFactory;
                break;
            case OUTDOOR:
                vendorFactory = outdoorVendorFactory;
                break;
            default:
                return ResponseEntity.badRequest()
                        .body(new VendorResponse(400, "Invalid vendor type"));
        }

        Vendor newVendor = vendorFactory.createVendor(vendorCreationDto);
        setCommonVendorProperties(newVendor, vendorCreationDto);

        try {
            vendorRepository.save(newVendor);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving Vendor into database", e);
        }

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

    //    @Override
    //    public ResponseEntity<VendorResponse> createVendor(VendorCreationDto vendorCreationDto) {
    //        validateVendorCreationData(vendorCreationDto);
    //
    //        Vendor newVendor;
    //        if (vendorCreationDto.getVendorType() == VendorType.INDOOR) {
    //            IndoorVendor indoorVendor = new IndoorVendor();
    //            indoorVendor.setConversationFriendly(vendorCreationDto.getConversationFriendly());
    //            newVendor = indoorVendor;
    //        } else if (vendorCreationDto.getVendorType() == VendorType.OUTDOOR) {
    //            OutdoorVendor outdoorVendor = new OutdoorVendor();
    //            outdoorVendor.setIntensityLevel(vendorCreationDto.getIntensityLevel());
    //            newVendor = outdoorVendor;
    //        } else {
    //            return ResponseEntity.badRequest().body(new VendorResponse(400, "Invalid vendor
    // type"));
    //        }
    //
    //        newVendor.setVendorName(vendorCreationDto.getVendorName());
    //        newVendor.setActivityName(vendorCreationDto.getActivityName());
    //        newVendor.setAddress(vendorCreationDto.getAddress());
    //        newVendor.setPhoneNumber(vendorCreationDto.getPhoneNumber());
    //        newVendor.setWebsite(vendorCreationDto.getWebsite());
    //        newVendor.setPassionId(vendorCreationDto.getPassionId());
    //        newVendor.setVendorType(vendorCreationDto.getVendorType());
    //
    //        try {
    //            vendorRepository.save(newVendor);
    //        } catch (Exception e) {
    //            throw new DatabaseTransactionException("Error saving vendor into Database", e);
    //        }
    //
    //        VendorResponse response =
    //                VendorResponse.builder().status(200).message("Successfully created
    // vendor").build();
    //        return ResponseEntity.status(HttpStatus.OK).body(response);
    //    }

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
