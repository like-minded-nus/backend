/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserUpgradePremiumDtoTest {

    @Test
    void testEquals() {
        UserUpgradePremiumDto dto1 = new UserUpgradePremiumDto(1);
        UserUpgradePremiumDto dto2 = new UserUpgradePremiumDto(1);
        UserUpgradePremiumDto dto3 = new UserUpgradePremiumDto(2);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        UserUpgradePremiumDto dto1 = new UserUpgradePremiumDto(1);
        UserUpgradePremiumDto dto2 = new UserUpgradePremiumDto(1);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
