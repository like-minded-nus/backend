/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VoucherCreationDtoTest {

    @Test
    void testEquals() {
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        VoucherCreationDto dto1 = new VoucherCreationDto("GYMMMM", endDate, 1, 100, true, 10);
        VoucherCreationDto dto2 = new VoucherCreationDto("GYMMMM", endDate, 1, 100, true, 10);
        VoucherCreationDto dto3 = new VoucherCreationDto("CYCLEEE", endDate, 2, 50, false, 20);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        VoucherCreationDto dto1 = new VoucherCreationDto("GYMMMM", endDate, 1, 100, true, 10);
        VoucherCreationDto dto2 = new VoucherCreationDto("GYMMMM", endDate, 1, 100, true, 10);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
