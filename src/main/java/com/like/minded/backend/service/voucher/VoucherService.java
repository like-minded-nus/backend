package com.like.minded.backend.service.voucher;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoucherService {
    ResponseEntity<VoucherResponse> createVoucher(VoucherCreationDto voucherCreationDto);

    ResponseEntity<VoucherResponse> updateVoucher(Integer voucherId, VoucherCreationDto updatedVoucherDto);

    Voucher getVoucherById(Integer voucherId);

    List<Voucher> getAllVouchers();

    List<Voucher> getVouchersByVendorId(Integer vendorId);
}
