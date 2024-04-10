/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.profile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProfilePassionTest {

    @Test
    void testProfilePassionCreationAndProperties() {
        Integer profileId = 1;
        Integer passionId = 1;

        ProfilePassion profilePassion =
                ProfilePassion.builder().profileId(profileId).passionId(passionId).build();

        assertNotNull(profilePassion, "ProfilePassion object should not be null");
        assertEquals(
                profileId,
                profilePassion.getProfileId(),
                "Profile ID should match the provided value");
        assertEquals(
                passionId,
                profilePassion.getPassionId(),
                "Passion ID should match the provided value");
    }

    @Test
    void testEqualsWithSameValues() {
        ProfilePassion profilePassion1 =
                ProfilePassion.builder().passionProfileId(1).profileId(1).passionId(2).build();

        ProfilePassion profilePassion2 =
                ProfilePassion.builder().passionProfileId(1).profileId(1).passionId(2).build();

        assertEquals(
                profilePassion1,
                profilePassion2,
                "ProfilePassions with the same values should be considered equal.");
    }

    @Test
    void testEqualsWithDifferentValues() {
        ProfilePassion profilePassion1 = ProfilePassion.builder().profileId(1).passionId(2).build();

        ProfilePassion profilePassion2 = ProfilePassion.builder().profileId(3).passionId(4).build();

        assertNotEquals(
                profilePassion1,
                profilePassion2,
                "ProfilePassions with different values should not be considered equal.");
    }

    @Test
    void testEqualsWithNull() {
        ProfilePassion profilePassion = ProfilePassion.builder().profileId(1).passionId(2).build();

        assertNotEquals(
                profilePassion,
                null,
                "ProfilePassion compared with null should not be considered equal.");
    }

    @Test
    void testEqualsWithDifferentType() {
        ProfilePassion profilePassion = ProfilePassion.builder().profileId(1).passionId(2).build();
        String notAProfilePassion = "Not a ProfilePassion";

        assertNotEquals(
                profilePassion,
                notAProfilePassion,
                "ProfilePassion compared with a different type should not be considered equal.");
    }

    @Test
    void testHashCodeConsistency() {
        ProfilePassion profilePassion = new ProfilePassion(1, 100, 200);
        int initialHashCode = profilePassion.hashCode();

        assertEquals(
                initialHashCode,
                profilePassion.hashCode(),
                "Hash code should remain consistent across invocations.");
    }

    @Test
    void testEqualObjectsHaveSameHashCode() {
        ProfilePassion profilePassion1 = new ProfilePassion(1, 100, 200);
        ProfilePassion profilePassion2 = new ProfilePassion(1, 100, 200);

        assertEquals(
                profilePassion1.hashCode(),
                profilePassion2.hashCode(),
                "Equal objects must have the same hash code.");
    }

    @Test
    void testDifferentObjectsDifferentHashCodes() {
        ProfilePassion profilePassion1 = new ProfilePassion(1, 100, 200);
        ProfilePassion profilePassion2 = new ProfilePassion(2, 101, 201);

        assertNotEquals(
                profilePassion1.hashCode(),
                profilePassion2.hashCode(),
                "Different objects ideally have different hash codes.");
    }
}
