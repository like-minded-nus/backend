package com.like.minded.backend.domain.profile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfilePassionTest {

    @Test
    void testProfilePassionCreationAndProperties() {
        Integer profileId = 1;
        Integer passionId = 1;

        ProfilePassion profilePassion = ProfilePassion.builder()
                .profileId(profileId)
                .passionId(passionId)
                .build();

        assertNotNull(profilePassion, "ProfilePassion object should not be null");
        assertEquals(profileId, profilePassion.getProfileId(), "Profile ID should match the provided value");
        assertEquals(passionId, profilePassion.getPassionId(), "Passion ID should match the provided value");
    }
}
