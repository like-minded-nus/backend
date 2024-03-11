package com.like.minded.backend.service.voucher;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.VoucherException;
import com.like.minded.backend.repository.voucher.VoucherRepository;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public ResponseEntity<VoucherResponse> createVoucher(VoucherCreationDto voucherCreationDto) {
        validateVoucherCreationData(voucherCreationDto);

        Voucher newVoucher = Voucher.builder()
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

        VoucherResponse response = VoucherResponse.builder()
                .status(200)
                .message("Successfully created voucher")
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
}
