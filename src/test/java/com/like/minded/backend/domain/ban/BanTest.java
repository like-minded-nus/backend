/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.ban;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BanTest {

    @Test
    void getId() {
        Ban ban = new Ban();
        ban.setId(1);
        assertEquals(1, ban.getId());
    }

    @Test
    void getUserId() {
        Ban ban = new Ban();
        ban.setUserId(1001);
        assertEquals(1001, ban.getUserId());
    }

    @Test
    void getBannedReason() {
        Ban ban = new Ban();
        ban.setBannedReason("Spamming");
        assertEquals("Spamming", ban.getBannedReason());
    }

    @Test
    void setId() {
        Ban ban = new Ban();
        ban.setId(2);
        assertEquals(2, ban.getId());
    }

    @Test
    void setUserId() {
        Ban ban = new Ban();
        ban.setUserId(1002);
        assertEquals(1002, ban.getUserId());
    }

    @Test
    void setBannedReason() {
        Ban ban = new Ban();
        ban.setBannedReason("Abuse");
        assertEquals("Abuse", ban.getBannedReason());
    }

    @Test
    void testToString() {
        Ban ban = new Ban(1, 1003, "Harassment");
        String expected = "Ban(id=1, userId=1003, bannedReason=Harassment)";
        assertEquals(expected, ban.toString());
    }

    @Test
    void testEquals() {
        Ban ban1 = new Ban(1, 1004, "Cheating");
        Ban ban2 = new Ban(1, 1004, "Cheating");
        assertEquals(ban1, ban2);
    }

    @Test
    void canEqual() {
        Ban ban1 = new Ban(1, 1005, "Exploiting");
        Ban ban2 = new Ban(1, 1005, "Exploiting");
        assertTrue(ban1.canEqual(ban2) && ban2.canEqual(ban1));
    }

    @Test
    void testHashCode() {
        Ban ban = new Ban(1, 1006, "Toxicity");
        assertNotNull(ban.hashCode());
    }

    @Test
    void builder() {
        Ban ban = Ban.builder().id(3).userId(1007).bannedReason("Griefing").build();
        assertNotNull(ban);
        assertEquals(3, ban.getId());
        assertEquals(1007, ban.getUserId());
        assertEquals("Griefing", ban.getBannedReason());
    }
}
