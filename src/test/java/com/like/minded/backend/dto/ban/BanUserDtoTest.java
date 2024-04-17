/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.ban;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BanUserDtoTest {

    @Test
    void testEquals() {
        BanUserDto dto1 = new BanUserDto(1, 100, "Spamming");
        BanUserDto dto2 = new BanUserDto(1, 100, "Spamming");
        BanUserDto dto3 = new BanUserDto(2, 101, "Abusive Language");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        BanUserDto dto1 = new BanUserDto(1, 100, "Spamming");
        BanUserDto dto2 = new BanUserDto(1, 100, "Spamming");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
