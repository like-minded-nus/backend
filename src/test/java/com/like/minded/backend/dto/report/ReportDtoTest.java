/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.report;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReportDtoTest {

    @Test
    void testEquals() {
        ReportDto dto1 = new ReportDto(1, 100, 200, "Spam");
        ReportDto dto2 = new ReportDto(1, 100, 200, "Spam");
        ReportDto dto3 = new ReportDto(2, 101, 201, "Harassment");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        ReportDto dto1 = new ReportDto(1, 100, 200, "Spam");
        ReportDto dto2 = new ReportDto(1, 100, 200, "Spam");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
