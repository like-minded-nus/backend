/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserRegistrationDtoTest {

    @Test
    void testEquals() {
        UserRegistrationDto dto1 =
                new UserRegistrationDto(
                        "johnDoe", "john.doe@example.com", "password123", "password123");
        UserRegistrationDto dto2 =
                new UserRegistrationDto(
                        "johnDoe", "john.doe@example.com", "password123", "password123");
        UserRegistrationDto dto3 =
                new UserRegistrationDto(
                        "janeDoe", "jane.doe@example.com", "password321", "password321");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        UserRegistrationDto dto1 =
                new UserRegistrationDto(
                        "johnDoe", "john.doe@example.com", "password123", "password123");
        UserRegistrationDto dto2 =
                new UserRegistrationDto(
                        "johnDoe", "john.doe@example.com", "password123", "password123");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
