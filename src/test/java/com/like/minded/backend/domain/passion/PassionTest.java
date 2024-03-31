package com.like.minded.backend.domain.passion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassionTest {

    @Test
    void testPassionCreation() {
        String passionName = "Music";

        Passion passion = Passion.builder()
                .passionName(passionName)
                .build();

        assertNotNull(passion, "Passion object should not be null");
        assertEquals(passionName, passion.getPassionName(), "Passion name should match the provided value");
    }
}