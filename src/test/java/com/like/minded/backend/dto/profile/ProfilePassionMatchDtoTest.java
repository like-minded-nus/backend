/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProfilePassionMatchDtoTest {

    @Test
    void testEquals() {
        ProfilePassionMatchDto dto1 = new ProfilePassionMatchDto(1, 90);
        ProfilePassionMatchDto dto2 = new ProfilePassionMatchDto(1, 90);
        ProfilePassionMatchDto dto3 = new ProfilePassionMatchDto(2, 85);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        ProfilePassionMatchDto dto1 = new ProfilePassionMatchDto(1, 90);
        ProfilePassionMatchDto dto2 = new ProfilePassionMatchDto(1, 90);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
