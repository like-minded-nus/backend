/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarkMessageAsReadDtoTest {

    @Test
    void testEquals() {
        MarkMessageAsReadDto dto1 = new MarkMessageAsReadDto(1);
        MarkMessageAsReadDto dto2 = new MarkMessageAsReadDto(1);
        MarkMessageAsReadDto dto3 = new MarkMessageAsReadDto(2);

        assertEquals(dto1, dto2, "DTOs with the same messageId should be equal");
        assertNotEquals(dto1, dto3, "DTOs with different messageId should not be equal");
        assertNotEquals(dto2, null, "DTO should not be equal to null");
        assertNotEquals(dto1, new Object(), "DTO should not be equal to a different type");
    }

    @Test
    void testHashCode() {
        MarkMessageAsReadDto dto1 = new MarkMessageAsReadDto(1);
        MarkMessageAsReadDto dto2 = new MarkMessageAsReadDto(1);
        MarkMessageAsReadDto dto3 = new MarkMessageAsReadDto(2);

        assertEquals(
                dto1.hashCode(),
                dto2.hashCode(),
                "Hash code should be the same for DTOs with the same messageId");
        assertNotEquals(
                dto1.hashCode(),
                dto3.hashCode(),
                "Hash code should be different for DTOs with different messageId");
    }
}
