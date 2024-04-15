/* LikeMinded (C)2024 */
package com.like.minded.backend.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorTypeTest {

    @Test
    void getDisplayName() {
        // Test to ensure the display names are correctly returned for each enum constant
        assertEquals(
                "Indoor",
                VendorType.INDOOR.getDisplayName(),
                "DisplayName for INDOOR should be 'Indoor'");
        assertEquals(
                "Outdoor",
                VendorType.OUTDOOR.getDisplayName(),
                "DisplayName for OUTDOOR should be 'Outdoor'");
    }

    @Test
    void values() {
        // Test to ensure all enum values are present
        VendorType[] expectedValues = {VendorType.INDOOR, VendorType.OUTDOOR};
        VendorType[] actualValues = VendorType.values();
        assertArrayEquals(
                expectedValues,
                actualValues,
                "Should return all defined enum values in the order they are declared");
    }

    @Test
    void valueOf() {
        // Test to ensure valueOf returns the correct enum constant for a given string
        assertEquals(
                VendorType.INDOOR,
                VendorType.valueOf("INDOOR"),
                "valueOf 'INDOOR' should return VendorType.INDOOR");
        assertEquals(
                VendorType.OUTDOOR,
                VendorType.valueOf("OUTDOOR"),
                "valueOf 'OUTDOOR' should return VendorType.OUTDOOR");

        // Test to ensure valueOf throws an exception for an invalid constant
        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            VendorType.valueOf("SUBTERRANEAN");
                        },
                        "valueOf with non-existent constant should throw IllegalArgumentException");

        assertTrue(
                exception.getMessage().contains("No enum constant"),
                "The exception message should indicate the missing enum constant");
    }
}
