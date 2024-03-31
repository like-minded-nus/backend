package com.like.minded.backend.domain.profile;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void testProfileCreationAndProperties() {
        Integer userId = 1;
        String displayName = "John Doe";
        String gender = "male";
        LocalDate birthdate = LocalDate.of(1990, 12, 1);
        String bio = "bio";

        Profile profile = Profile.builder()
                .userId(userId)
                .displayName(displayName)
                .gender(gender)
                .birthdate(birthdate)
                .bio(bio)
                .build();

        assertNotNull(profile, "Profile object should not be null");
        assertEquals(userId, profile.getUserId(), "User ID should match the provided value");
        assertEquals(displayName, profile.getDisplayName(), "Display Name should match the provided value");
        assertEquals(gender, profile.getGender(), "Gender should match the provided value");
        assertEquals(birthdate, profile.getBirthdate(), "Birthdate should match the provided value");
        assertEquals(bio, profile.getBio(), "Bio should match the provided value");
    }

    @Test
    void testEqualsWithSameValues() {
        Profile profile1 = createTestProfile();
        Profile profile2 = createTestProfile();

        assertEquals(profile1, profile2, "Profiles with the same values should be considered equal.");
    }

    @Test
    void testEqualsWithDifferentValues() {
        Profile profile1 = createTestProfile();
        Profile profile2 = Profile.builder()
                .profileId(1)
                .userId(100)
                .displayName("Another Name")
                .gender("female")
                .birthdate(LocalDate.of(2000, 8, 12))
                .bio("another bio")
                .build();

        assertNotEquals(profile1, profile2, "Profiles with different values should not be considered equal.");
    }

    @Test
    void testEqualsWithNull() {
        Profile profile = createTestProfile();

        assertNotEquals(profile, null, "Profile compared with null should not be considered equal.");
    }

    @Test
    void testEqualsWithDifferentType() {
        Profile profile = createTestProfile();
        String notAProfile = "Not a Profile";

        assertNotEquals(profile, notAProfile, "Profile compared with a different type should not be considered equal.");
    }

    @Test
    void testHashCodeConsistency() {
        Profile profile = Profile.builder()
                .profileId(1)
                .userId(100)
                .displayName("John Doe")
                .gender("Male")
                .birthdate(LocalDate.of(1990, 1, 1))
                .bio("A brief bio")
                .build();

        int hashCode1 = profile.hashCode();
        int hashCode2 = profile.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash code should remain consistent across invocations.");
    }

    @Test
    void testEqualObjectsHaveSameHashCode() {
        Profile profile1 = createTestProfile();

        Profile profile2 = createTestProfile();

        assertEquals(profile1, profile2, "Profiles should be considered equal.");
        assertEquals(profile1.hashCode(), profile2.hashCode(), "Equal objects must have the same hash code.");
    }

    @Test
    void testDifferentObjectsDifferentHashCodes() {
        Profile profile1 = Profile.builder()
                .userId(1)
                .displayName("John Doe")
                .gender("male")
                .birthdate(LocalDate.of(1990, 12, 1))
                .bio("bio")
                .build();

        Profile profile2 = Profile.builder()
                .userId(2)
                .displayName("Jane Doe")
                .gender("female")
                .birthdate(LocalDate.of(1995, 8, 1))
                .bio("another bio")
                .build();

        assertNotEquals(profile1.hashCode(), profile2.hashCode(), "Different objects ideally have different hash codes.");
    }

    // Helper method to create a test profile with consistent values
    private Profile createTestProfile() {
        return Profile.builder()
                .profileId(1)
                .userId(1)
                .displayName("Test Name")
                .gender("male")
                .birthdate(LocalDate.of(1990, 12, 1))
                .bio("bio")
                .build();
    }
}
