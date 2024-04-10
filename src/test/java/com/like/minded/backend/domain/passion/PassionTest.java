/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.passion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PassionTest {

    @Test
    void testPassionCreation() {
        String passionName = "Music";

        Passion passion = Passion.builder().passionName(passionName).build();

        assertNotNull(passion, "Passion object should not be null");
        assertEquals(
                passionName,
                passion.getPassionName(),
                "Passion name should match the provided value");
    }

    @Test
    void testEqualsWithSameValues() {
        Passion passion1 = Passion.builder().passionId(1).passionName("Music").build();
        Passion passion2 = Passion.builder().passionId(1).passionName("Music").build();

        assertEquals(
                passion1, passion2, "Passions with the same values should be considered equal.");
    }

    @Test
    void testEqualsWithDifferentValues() {
        Passion passion1 = Passion.builder().passionId(1).passionName("Music").build();
        Passion passion2 = Passion.builder().passionId(2).passionName("Art").build();

        assertNotEquals(
                passion1,
                passion2,
                "Passions with different values should not be considered equal.");
    }

    @Test
    void testEqualsWithNull() {
        Passion passion = Passion.builder().passionId(1).passionName("Music").build();

        assertNotEquals(
                passion, null, "Passion compared with null should not be considered equal.");
    }

    @Test
    void testEqualsWithDifferentType() {
        Passion passion = Passion.builder().passionId(1).passionName("Music").build();
        String notAPassion = "Not a Passion";

        assertNotEquals(
                passion,
                notAPassion,
                "Passion compared with a different type should not be considered equal.");
    }

    @Test
    void testHashCode() {
        Passion passion1 = new Passion(null, "Music");
        Passion passion2 = new Passion(null, "Music");

        assertEquals(
                passion1.hashCode(),
                passion2.hashCode(),
                "Two passions with the same name must have the same hash code.");

        Passion passionModified = new Passion(null, "Art");

        assertNotEquals(
                passion1.hashCode(),
                passionModified.hashCode(),
                "Two passions with different names are likely to have different hash codes.");
    }

    @Test
    void testHashCodeConsistency() {
        Passion passion = new Passion(null, "Music");
        int initialHashCode = passion.hashCode();

        assertEquals(
                initialHashCode,
                passion.hashCode(),
                "Hash code should remain consistent across calls.");
    }
}
