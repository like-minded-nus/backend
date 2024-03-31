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
}
