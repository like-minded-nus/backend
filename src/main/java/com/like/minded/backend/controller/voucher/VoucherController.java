package com.like.minded.backend.controller.voucher;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.service.voucher.VoucherService;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vouchers")
@CrossOrigin(origins = "*")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @PostMapping("/create_voucher")
    public ResponseEntity<VoucherResponse> createVoucher(@RequestBody VoucherCreationDto voucherCreationDto) {
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
}
