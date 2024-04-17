/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.report;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetReportsDtoTest {

    @Test
    void testEquals() {
        GetReportsDto dto1 = new GetReportsDto(1, 100, "johnDoe", 200, "Spam");
        GetReportsDto dto2 = new GetReportsDto(1, 100, "johnDoe", 200, "Spam");
        GetReportsDto dto3 = new GetReportsDto(2, 101, "janeDoe", 201, "Harassment");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        GetReportsDto dto1 = new GetReportsDto(1, 100, "johnDoe", 200, "Spam");
        GetReportsDto dto2 = new GetReportsDto(1, 100, "johnDoe", 200, "Spam");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
