/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserLoginDtoTest {

    @Test
    void testEquals() {
        UserLoginDto dto1 = new UserLoginDto("johnDoe", "password123");
        UserLoginDto dto2 = new UserLoginDto("johnDoe", "password123");
        UserLoginDto dto3 = new UserLoginDto("janeDoe", "password321");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        UserLoginDto dto1 = new UserLoginDto("johnDoe", "password123");
        UserLoginDto dto2 = new UserLoginDto("johnDoe", "password123");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
