/* LikeMinded (C)2024 */
package com.like.minded.backend.service.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface VoucherService {
    ResponseEntity<VoucherResponse> createVoucher(VoucherCreationDto voucherCreationDto);

    ResponseEntity<VoucherResponse> updateVoucher(
            Integer voucherId, VoucherCreationDto updatedVoucherDto);

    Voucher getVoucherById(Integer voucherId);

    List<Voucher> getAllVouchers();

    List<Voucher> getVouchersByVendorId(Integer vendorId);

    ResponseEntity<VoucherResponse> deleteVoucher(Integer voucherId);
}
