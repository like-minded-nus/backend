/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.match;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchRequestBodyDtoTest {

    @Test
    void testEquals() {
        MatchRequestBodyDto dto1 = new MatchRequestBodyDto(1, 2, true);
        MatchRequestBodyDto dto2 = new MatchRequestBodyDto(1, 2, true);
        MatchRequestBodyDto dto3 = new MatchRequestBodyDto(2, 3, false);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        MatchRequestBodyDto dto1 = new MatchRequestBodyDto(1, 2, true);
        MatchRequestBodyDto dto2 = new MatchRequestBodyDto(1, 2, true);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
