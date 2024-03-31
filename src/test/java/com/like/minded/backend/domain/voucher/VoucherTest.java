package com.like.minded.backend.domain.voucher;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class VoucherTest {

    @Test
    void testVoucherCreationAndProperties() {
        String voucherName = "Discount 10%";
        LocalDate voucherEndDate = LocalDate.of(2024, 12, 31);
        String voucherDescription = "10% off on all products";
        boolean redeemStatus = false;
        Integer vendorId = 1;

        Voucher voucher = Voucher.builder()
                .voucherName(voucherName)
                .voucherEndDate(voucherEndDate)
                .voucherDescription(voucherDescription)
                .redeemStatus(redeemStatus)
                .vendorId(vendorId)
                .build();

        assertNotNull(voucher, "Voucher object should not be null");
        assertEquals(voucherName, voucher.getVoucherName(), "Voucher name should match the provided value");
        assertEquals(voucherEndDate, voucher.getVoucherEndDate(), "Voucher end date should match the provided value");
        assertEquals(voucherDescription, voucher.getVoucherDescription(), "Voucher description should match the provided value");
        assertEquals(redeemStatus, voucher.isRedeemStatus(), "Redeem status should match the provided value");
        assertEquals(vendorId, voucher.getVendorId(), "Vendor ID should match the provided value");
    }
}
