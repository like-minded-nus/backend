/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.voucher;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.domain.voucher.VoucherType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VoucherResponseDtoTests {

    @Test
    void testEquals() {
        // Create two instances with the same data
        VoucherResponseDto voucher1 =
                VoucherResponseDto.builder()
                        .voucherId(1)
                        .voucherName("Holiday Discount")
                        .voucherType(new VoucherType(1000, "Voucher"))
                        .voucherAmount(20)
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .redeemStatus(false)
                        .vendorId(101)
                        .build();

        VoucherResponseDto voucher2 =
                VoucherResponseDto.builder()
                        .voucherId(1)
                        .voucherName("Holiday Discount")
                        .voucherType(new VoucherType(1000, "Voucher"))
                        .voucherAmount(20)
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .redeemStatus(false)
                        .vendorId(101)
                        .build();

        assertEquals(voucher1, voucher2, "VoucherResponseDto instances should be equal");
    }

    @Test
    void testHashCode() {
        // Create an instance
        VoucherResponseDto voucher =
                VoucherResponseDto.builder()
                        .voucherId(1)
                        .voucherName("Holiday Discount")
                        .voucherType(new VoucherType(1000, "Voucher"))
                        .voucherAmount(20)
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .redeemStatus(false)
                        .vendorId(101)
                        .build();

        // Another instance with the same data
        VoucherResponseDto sameVoucher =
                VoucherResponseDto.builder()
                        .voucherId(1)
                        .voucherName("Holiday Discount")
                        .voucherType(new VoucherType(1000, "Voucher"))
                        .voucherAmount(20)
                        .voucherEndDate(LocalDate.of(2023, 12, 31))
                        .redeemStatus(false)
                        .vendorId(101)
                        .build();

        assertEquals(
                voucher.hashCode(),
                sameVoucher.hashCode(),
                "Hash codes should be equal for equal objects");
    }
}
