/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReadMessageDtoTest {

    @Test
    void testEquals() {
        ReadMessageDto dto1 = new ReadMessageDto(1, 10);
        ReadMessageDto dto2 = new ReadMessageDto(1, 10);
        ReadMessageDto dto3 = new ReadMessageDto(2, 20);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        ReadMessageDto dto1 = new ReadMessageDto(1, 10);
        ReadMessageDto dto2 = new ReadMessageDto(1, 10);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
