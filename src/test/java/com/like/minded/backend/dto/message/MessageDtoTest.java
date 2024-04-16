/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import org.junit.jupiter.api.Test;

class MessageDtoTest {

    @Test
    void testEquals() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        MessageDto dto1 = new MessageDto(1, 10, 20, "Hello", now, "yes");
        MessageDto dto2 = new MessageDto(1, 10, 20, "Hello", now, "yes");
        MessageDto dto3 = new MessageDto(2, 30, 40, "Hi", now, "no");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        MessageDto dto1 = new MessageDto(1, 10, 20, "Hello", now, "yes");
        MessageDto dto2 = new MessageDto(1, 10, 20, "Hello", now, "yes");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
