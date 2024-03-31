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

    @Test
    void testEquals() {
        Voucher voucher1 = Voucher.builder()
                .voucherId(1)
                .voucherName("10% Off")
                .voucherEndDate(LocalDate.of(2023, 12, 31))
                .voucherDescription("Get 10% off on your next purchase")
                .redeemStatus(false)
                .vendorId(1)
                .build();

        Voucher voucher2 = Voucher.builder()
                .voucherId(1)
                .voucherName("10% Off")
                .voucherEndDate(LocalDate.of(2023, 12, 31))
                .voucherDescription("Get 10% off on your next purchase")
                .redeemStatus(false)
                .vendorId(1)
                .build();

        Voucher voucherDifferent = Voucher.builder()
                .voucherId(2)
                .voucherName("20% Off")
                .build();

        assertEquals(voucher1, voucher2, "Vouchers with the same values should be considered equal.");
        assertNotEquals(voucher1, voucherDifferent, "Different vouchers should not be considered equal.");
    }

    @Test
    void testHashCode() {
        Voucher voucher1 = Voucher.builder()
                .voucherId(1)
                .voucherName("10% Off")
                .voucherEndDate(LocalDate.of(2023, 12, 31))
                .voucherDescription("Get 10% off on your next purchase")
                .redeemStatus(false)
                .vendorId(1)
                .build();

        Voucher voucher2 = Voucher.builder()
                .voucherId(1)
                .voucherName("10% Off")
                .voucherEndDate(LocalDate.of(2023, 12, 31))
                .voucherDescription("Get 10% off on your next purchase")
                .redeemStatus(false)
                .vendorId(1)
                .build();

        assertEquals(voucher1.hashCode(), voucher2.hashCode(), "Equal vouchers must have the same hash code.");

        Voucher voucherDifferent = Voucher.builder()
                .voucherId(2)
                .voucherName("20% Off")
                .build();

        assertNotEquals(voucher1.hashCode(), voucherDifferent.hashCode(), "Different vouchers should ideally have different hash codes.");
    }
}
