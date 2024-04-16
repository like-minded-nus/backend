/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserDtoTest {

    @Test
    void testEquals() {
        UserDto dto1 = new UserDto(1, "johnDoe", "john.doe@example.com", 1, 1, false);
        UserDto dto2 = new UserDto(1, "johnDoe", "john.doe@example.com", 1, 1, false);
        UserDto dto3 = new UserDto(2, "janeDoe", "jane.doe@example.com", 2, 0, true);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        UserDto dto1 = new UserDto(1, "johnDoe", "john.doe@example.com", 1, 1, false);
        UserDto dto2 = new UserDto(1, "johnDoe", "john.doe@example.com", 1, 1, false);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
