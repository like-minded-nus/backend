/* LikeMinded (C)2024 */
package com.like.minded.backend.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversationFriendlyTest {

    @Test
    void getDisplayName() {
        assertEquals(
                "Yes",
                ConversationFriendly.YES.getDisplayName(),
                "DisplayName for YES should be 'Yes'");
        assertEquals(
                "No",
                ConversationFriendly.NO.getDisplayName(),
                "DisplayName for NO should be 'No'");
    }

    @Test
    void values() {
        ConversationFriendly[] expectedValues = {ConversationFriendly.YES, ConversationFriendly.NO};
        ConversationFriendly[] actualValues = ConversationFriendly.values();
        assertArrayEquals(expectedValues, actualValues, "Should return all available enum values");
    }

    @Test
    void valueOf() {
        assertEquals(
                ConversationFriendly.YES,
                ConversationFriendly.valueOf("YES"),
                "valueOf 'YES' should return ConversationFriendly.YES");
        assertEquals(
                ConversationFriendly.NO,
                ConversationFriendly.valueOf("NO"),
                "valueOf 'NO' should return ConversationFriendly.NO");

        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            ConversationFriendly.valueOf("MAYBE");
                        },
                        "valueOf with non-existent constant should throw IllegalArgumentException");

        assertTrue(
                exception.getMessage().contains("No enum constant"),
                "The exception message should indicate the missing enum constant");
    }
}
