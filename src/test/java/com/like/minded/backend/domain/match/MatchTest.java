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
}