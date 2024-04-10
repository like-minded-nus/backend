/* LikeMinded (C)2024 */
package com.like.minded.backend.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.domain.voucher.Voucher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class CreatedUpdatedColumnsTest {

    @Test
    void testCreatedUpdatedColumnsInheritance() {
        LocalDateTime beforeCreation = LocalDateTime.now();

        // Use vendor to test extended class `CreatedUpdatedColumns`
        String voucherName = "Discount 10%";
        LocalDate voucherEndDate = LocalDate.of(2024, 12, 31);
        String voucherDescription = "10% off on all products";
        boolean redeemStatus = false;
        Integer vendorId = 1;

        Voucher testEntity =
                Voucher.builder()
                        .voucherName(voucherName)
                        .voucherEndDate(voucherEndDate)
                        .voucherDescription(voucherDescription)
                        .redeemStatus(redeemStatus)
                        .vendorId(vendorId)
                        .build();

        // Wait a bit to ensure a time difference
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LocalDateTime afterCreation = LocalDateTime.now();

        assertNotNull(testEntity.getCreatedDate(), "Created date should not be null");
        assertTrue(
                testEntity.getCreatedDate().isAfter(beforeCreation)
                        && testEntity.getCreatedDate().isBefore(afterCreation),
                "Created date should be within the test's time frame");
        assertNotNull(testEntity.getUpdatedDate(), "Updated date should not be null");
        assertEquals("SYSTEM", testEntity.getCreatedBy(), "Created by should be SYSTEM");
        assertEquals("SYSTEM", testEntity.getUpdatedBy(), "Updated by should be SYSTEM");
    }
}
