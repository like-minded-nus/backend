/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.service.voucher.VoucherService;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vouchers")
@CrossOrigin(origins = "*")
public class VoucherController {
    @Autowired private VoucherService voucherService;

    @PostMapping("/create_voucher")
    public ResponseEntity<VoucherResponse> createVoucher(
            @RequestBody VoucherCreationDto voucherCreationDto) {
        return voucherService.createVoucher(voucherCreationDto);
    }

    @GetMapping("")
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        if (vouchers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/{voucherId}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable Integer voucherId) {
        Voucher voucher = voucherService.getVoucherById(voucherId);
        if (voucher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/vendor/{vendorId}/vouchers")
    public ResponseEntity<List<Voucher>> getVouchersByVendorId(@PathVariable Integer vendorId) {
        List<Voucher> vouchers = voucherService.getVouchersByVendorId(vendorId);
        if (vouchers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vouchers);
    }

    @PutMapping("/{voucherId}")
    public ResponseEntity<VoucherResponse> updateVoucher(
            @PathVariable Integer voucherId, @RequestBody VoucherCreationDto updatedVoucherDto) {
        ResponseEntity<VoucherResponse> responseEntity =
                voucherService.updateVoucher(voucherId, updatedVoucherDto);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{voucherId}")
    public ResponseEntity<VoucherResponse> deleteVoucher(@PathVariable Integer voucherId) {
        ResponseEntity<VoucherResponse> responseEntity = voucherService.deleteVoucher(voucherId);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
}
