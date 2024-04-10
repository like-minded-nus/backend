/* LikeMinded (C)2024 */
package com.like.minded.backend.service.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.VoucherException;
import com.like.minded.backend.repository.voucher.VoucherRepository;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    @Override
    public ResponseEntity<VoucherResponse> createVoucher(VoucherCreationDto voucherCreationDto) {
        validateVoucherCreationData(voucherCreationDto);

        Voucher newVoucher =
                Voucher.builder()
                        .voucherName(voucherCreationDto.getVoucherName())
                        .voucherEndDate(voucherCreationDto.getVoucherEndDate())
                        .voucherDescription(voucherCreationDto.getVoucherDescription())
                        .redeemStatus(voucherCreationDto.isRedeemStatus())
                        .vendorId(voucherCreationDto.getVendorId())
                        .build();

        try {
            voucherRepository.save(newVoucher);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving voucher into Database", e);
        }

        VoucherResponse response =
                VoucherResponse.builder()
                        .status(200)
                        .message("Successfully created voucher")
                        .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<VoucherResponse> updateVoucher(
            Integer voucherId, VoucherCreationDto updatedVoucherDto) {
        Voucher existingVoucher = voucherRepository.findById(voucherId).orElse(null);
        if (existingVoucher == null) {
            return ResponseEntity.notFound().build();
        }

        existingVoucher.setVoucherName(updatedVoucherDto.getVoucherName());
        existingVoucher.setVoucherEndDate(updatedVoucherDto.getVoucherEndDate());
        existingVoucher.setVoucherDescription(updatedVoucherDto.getVoucherDescription());
        existingVoucher.setRedeemStatus(updatedVoucherDto.isRedeemStatus());
        existingVoucher.setVendorId(updatedVoucherDto.getVendorId());

        try {
            voucherRepository.save(existingVoucher);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error updating voucher in the database", e);
        }

        VoucherResponse response =
                VoucherResponse.builder()
                        .status(200)
                        .message("Successfully updated voucher")
                        .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateVoucherCreationData(VoucherCreationDto voucherCreationDto) {
        if (voucherRepository.existsByVoucherName(voucherCreationDto.getVoucherName())) {
            throw new VoucherException("Vendor name already exists.");
        }
    }

    @Override
    public Voucher getVoucherById(Integer voucherId) {
        return voucherRepository.findById(voucherId).orElse(null);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public List<Voucher> getVouchersByVendorId(Integer vendorId) {
        return voucherRepository.findByVendorId(vendorId);
    }

    @Override
    public ResponseEntity<VoucherResponse> deleteVoucher(Integer voucherId) {
        Voucher existingVoucher = voucherRepository.findById(voucherId).orElse(null);
        if (existingVoucher == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            voucherRepository.deleteById(voucherId);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error deleting voucher from the database", e);
        }

        VoucherResponse response =
                VoucherResponse.builder()
                        .status(200)
                        .message("Voucher deleted successfully")
                        .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
