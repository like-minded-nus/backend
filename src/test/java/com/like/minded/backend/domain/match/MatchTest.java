package com.like.minded.backend.domain.match;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchCreation() {
        Integer profileId1 = 1;
        Integer profileId2 = 2;
        Boolean like1 = true;
        Boolean like2 = false;

        Match match = Match.builder()
                .profileId_1(profileId1)
                .profileId_2(profileId2)
                .like_1(like1)
                .like_2(like2)
                .build();

        assertNotNull(match);
        assertEquals(profileId1, match.getProfileId_1(), "Profile ID 1 should match the provided value");
        assertEquals(profileId2, match.getProfileId_2(), "Profile ID 2 should match the provided value");
        assertEquals(like1, match.getLike_1(), "Like 1 should match the provided value");
        assertEquals(like2, match.getLike_2(), "Like 2 should match the provided value");
    }

    @Test
    void testEqualsWithSameObject() {
        Match match = new Match(1, 100, 200, true, false);

        // When & Then
        assertEquals(match, match, "A match should be equal to itself");
    }

    @Test
    void testEqualsWithDifferentClassObject() {
        Match match = new Match(1, 100, 200, true, false);
        Object other = new Object();

        assertNotEquals(match, other, "A match should not be equal to an object of a different class");
    }

    @Test
    void testEqualsWithNull() {
        Match match = new Match(1, 100, 200, true, false);

        assertNotEquals(null, match, "A match should not be equal to null");
    }

    @Test
    void testEqualsWithDifferentData() {
        Match match1 = new Match(1, 100, 200, true, false);
        Match match2 = new Match(2, 101, 201, false, true);

        assertNotEquals(match1, match2, "Two matches with different data should not be equal");
    }

    @Test
    void testEqualsWithSameDataDifferentObject() {
        Match match1 = new Match(1, 100, 200, true, false);
        Match match2 = new Match(1, 100, 200, true, false);

        assertEquals(match1, match2, "Two matches with the same data should be equal");
    }

    @Test
    void testHashCode() {
        Match match1 = new Match(null, 1, 2, true, false);
        Match match2 = new Match(null, 1, 2, true, false);

        assertEquals(match1.hashCode(), match2.hashCode(), "Two matches with the same field values must have the same hash code.");

        Match matchModified = new Match(null, 1, 2, false, false);

        assertNotEquals(match1.hashCode(), matchModified.hashCode(), "Two matches with different field values are likely to have different hash codes.");
    }

    @Test
    void testHashCodeConsistency() {
        Match match = new Match(null, 1, 2, true, false);
        int initialHashCode = match.hashCode();

        assertEquals(initialHashCode, match.hashCode(), "Hash code should remain consistent across calls.");
    }
}