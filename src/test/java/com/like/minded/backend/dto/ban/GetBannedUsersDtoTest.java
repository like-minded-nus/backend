/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.ban;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetBannedUsersDtoTest {

    @Test
    void testEquals() {
        GetBannedUsersDto dto1 = new GetBannedUsersDto(1, 100, "johnDoe", "Spamming");
        GetBannedUsersDto dto2 = new GetBannedUsersDto(1, 100, "johnDoe", "Spamming");
        GetBannedUsersDto dto3 = new GetBannedUsersDto(2, 101, "janeDoe", "Abusive Language");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        GetBannedUsersDto dto1 = new GetBannedUsersDto(1, 100, "johnDoe", "Spamming");
        GetBannedUsersDto dto2 = new GetBannedUsersDto(1, 100, "johnDoe", "Spamming");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
