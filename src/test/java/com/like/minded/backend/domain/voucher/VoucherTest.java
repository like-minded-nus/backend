/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VoucherTest {

    @Test
    void testVoucherCreationAndProperties() {
        String voucherName = "Discount 10%";
        LocalDate voucherEndDate = LocalDate.of(2024, 12, 31);
        String voucherDescription = "10% off on all products";
        boolean redeemStatus = false;
        Integer vendorId = 1;
        VoucherType voucherType = new VoucherType();
        voucherType.setVoucherType(1);
        voucherType.setVoucherTypeDesc("Free Trial");
        Integer voucherAmount = 3;

        Voucher voucher =
                Voucher.builder()
                        .voucherName(voucherName)
                        .voucherEndDate(voucherEndDate)
                        .voucherType(voucherType)
                        .voucherAmount(voucherAmount)
                        .redeemStatus(redeemStatus)
                        .vendorId(vendorId)
                        .build();

        assertNotNull(voucher, "Voucher object should not be null");
        assertEquals(
                voucherName,
                voucher.getVoucherName(),
                "Voucher name should match the provided value");
        assertEquals(
                voucherEndDate,
                voucher.getVoucherEndDate(),
                "Voucher end date should match the provided value");
        assertEquals(
                voucherType,
                voucher.getVoucherType(),
                "Voucher description should match the provided value");
        assertEquals(
                voucherAmount,
                voucher.getVoucherAmount(),
                "Voucher amount should match the provided value");
        assertEquals(
                redeemStatus,
                voucher.isRedeemStatus(),
                "Redeem status should match the provided value");
        assertEquals(vendorId, voucher.getVendorId(), "Vendor ID should match the provided value");
    }

    @Test
    void testEquals() {
        VoucherType voucherType1 = new VoucherType();
        voucherType1.setVoucherType(1);
        voucherType1.setVoucherTypeDesc("Free Trial");

        VoucherType voucherType2 = new VoucherType();
        voucherType2.setVoucherType(2);
        voucherType2.setVoucherTypeDesc("Discount");

        Voucher voucher1 =
                Voucher.builder()
                        .voucherId(1)
                        .voucherName("10% Off")
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .voucherType(voucherType1)
                        .voucherAmount(3)
                        .redeemStatus(false)
                        .vendorId(1)
                        .build();

        Voucher voucher2 =
                Voucher.builder()
                        .voucherId(1)
                        .voucherName("10% Off")
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .voucherType(voucherType1)
                        .voucherAmount(3)
                        .redeemStatus(false)
                        .vendorId(1)
                        .build();

        Voucher voucherDifferent =
                Voucher.builder()
                        .voucherId(2)
                        .voucherName("20% Off")
                        .voucherType(voucherType2)
                        .voucherAmount(4)
                        .build();

        assertEquals(
                voucher1, voucher2, "Vouchers with the same values should be considered equal.");
        assertNotEquals(
                voucher1, voucherDifferent, "Different vouchers should not be considered equal.");
    }

    @Test
    void testHashCode() {
        VoucherType voucherType1 = new VoucherType();
        voucherType1.setVoucherType(1);
        voucherType1.setVoucherTypeDesc("Free Trial");

        VoucherType voucherType2 = new VoucherType();
        voucherType2.setVoucherType(2);
        voucherType2.setVoucherTypeDesc("Discount");

        Voucher voucher1 =
                Voucher.builder()
                        .voucherId(1)
                        .voucherName("10% Off")
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .voucherType(voucherType1)
                        .voucherAmount(3)
                        .redeemStatus(false)
                        .vendorId(1)
                        .build();

        Voucher voucher2 =
                Voucher.builder()
                        .voucherId(1)
                        .voucherName("10% Off")
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .voucherType(voucherType1)
                        .voucherAmount(3)
                        .redeemStatus(false)
                        .vendorId(1)
                        .build();

        assertEquals(
                voucher1.hashCode(),
                voucher2.hashCode(),
                "Equal vouchers must have the same hash code.");

        Voucher voucherDifferent =
                Voucher.builder()
                        .voucherId(2)
                        .voucherName("20% Off")
                        .voucherType(voucherType2)
                        .voucherAmount(4)
                        .build();

        assertNotEquals(
                voucher1.hashCode(),
                voucherDifferent.hashCode(),
                "Different vouchers should ideally have different hash codes.");
    }
}
