/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetMessageRequestBodyDtoTest {

    @Test
    void testEquals() {
        GetMessageRequestBodyDto dto1 = new GetMessageRequestBodyDto(10, 20);
        GetMessageRequestBodyDto dto2 = new GetMessageRequestBodyDto(10, 20);
        GetMessageRequestBodyDto dto3 = new GetMessageRequestBodyDto(20, 30);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        GetMessageRequestBodyDto dto1 = new GetMessageRequestBodyDto(10, 20);
        GetMessageRequestBodyDto dto2 = new GetMessageRequestBodyDto(10, 20);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
