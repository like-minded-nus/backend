/* LikeMinded (C)2024 */
package com.like.minded.backend.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.domain.voucher.VoucherType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

class ConcreteCreatedUpdatedColumns extends CreatedUpdatedColumns {}

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CreatedUpdatedColumnsTest {
    ConcreteCreatedUpdatedColumns columns = new ConcreteCreatedUpdatedColumns();

    @Test
    void testCreatedUpdatedColumnsInheritance() {
        LocalDateTime beforeCreation = LocalDateTime.now();

        // Use vendor to test extended class `CreatedUpdatedColumns`
        String voucherName = "Discount 10%";
        LocalDate voucherEndDate = LocalDate.of(2024, 12, 31);
        String voucherDescription = "10% off on all products";
        boolean redeemStatus = false;
        Integer vendorId = 1;

        VoucherType voucherType = new VoucherType();
        voucherType.setVoucherType(1);
        voucherType.setVoucherTypeDesc("Free Trial");
        Integer voucherAmount = 3;

        Voucher testEntity =
                Voucher.builder()
                        .voucherName(voucherName)
                        .voucherEndDate(voucherEndDate)
                        .voucherType(voucherType)
                        .voucherAmount(voucherAmount)
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

    @Test
    void getCreatedBy() {
        assertEquals("SYSTEM", columns.getCreatedBy());
    }

    @Test
    void getCreatedDate() {
        assertNotNull(columns.getCreatedDate());
    }

    @Test
    void getUpdatedBy() {
        assertEquals("SYSTEM", columns.getUpdatedBy());
    }

    @Test
    void getUpdatedDate() {
        assertNotNull(columns.getUpdatedDate());
    }

    @Test
    void setCreatedBy() {
        columns.setCreatedBy("USER1");
        assertEquals("USER1", columns.getCreatedBy());
    }

    @Test
    void setCreatedDate() {
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        columns.setCreatedDate(now);
        assertEquals(now, columns.getCreatedDate());
    }

    @Test
    void setUpdatedBy() {
        columns.setUpdatedBy("USER2");
        assertEquals("USER2", columns.getUpdatedBy());
    }

    @Test
    void setUpdatedDate() {
        LocalDateTime later = LocalDateTime.now().plusDays(2);
        columns.setUpdatedDate(later);
        assertEquals(later, columns.getUpdatedDate());
    }

    @Test
    void testToString() {
        String expected =
                String.format(
                        "CreatedUpdatedColumns(createdBy=SYSTEM, createdDate=%s,"
                                + " updatedBy=SYSTEM, updatedDate=%s)",
                        columns.getCreatedDate(), columns.getUpdatedDate());
        assertEquals(expected, columns.toString());
    }

    @Test
    void canEqual() {
        ConcreteCreatedUpdatedColumns other = new ConcreteCreatedUpdatedColumns();
        assertTrue(columns.canEqual(other) && other.canEqual(columns));
    }

    @Test
    void testHashCode() {
        assertNotNull(columns.hashCode());
    }
}
