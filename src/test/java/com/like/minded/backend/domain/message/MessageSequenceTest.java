/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessageSequenceTest {

    @Test
    void getSequenceId() {
        MessageSequence messageSequence = new MessageSequence();
        messageSequence.setSequenceId(1);
        assertEquals(1, messageSequence.getSequenceId());
    }

    @Test
    void getNextMessageId() {
        MessageSequence messageSequence = new MessageSequence();
        messageSequence.setNextMessageId(100);
        assertEquals(100, messageSequence.getNextMessageId());
    }

    @Test
    void setSequenceId() {
        MessageSequence messageSequence = new MessageSequence();
        messageSequence.setSequenceId(2);
        assertEquals(2, messageSequence.getSequenceId());
    }

    @Test
    void setNextMessageId() {
        MessageSequence messageSequence = new MessageSequence();
        messageSequence.setNextMessageId(101);
        assertEquals(101, messageSequence.getNextMessageId());
    }

    @Test
    void testToString() {
        MessageSequence messageSequence = new MessageSequence(1, 102);
        String expected = "MessageSequence(sequenceId=1, nextMessageId=102)";
        assertEquals(expected, messageSequence.toString());
    }

    @Test
    void testEquals() {
        MessageSequence messageSequence1 = new MessageSequence(1, 103);
        MessageSequence messageSequence2 = new MessageSequence(1, 103);
        assertEquals(messageSequence1, messageSequence2);
    }

    @Test
    void canEqual() {
        MessageSequence messageSequence1 = new MessageSequence(1, 104);
        MessageSequence messageSequence2 = new MessageSequence(1, 104);
        assertTrue(
                messageSequence1.canEqual(messageSequence2)
                        && messageSequence2.canEqual(messageSequence1));
    }

    @Test
    void testHashCode() {
        MessageSequence messageSequence = new MessageSequence(1, 105);
        assertNotNull(messageSequence.hashCode());
    }

    @Test
    void builder() {
        MessageSequence messageSequence =
                MessageSequence.builder().sequenceId(3).nextMessageId(106).build();
        assertNotNull(messageSequence);
        assertEquals(3, messageSequence.getSequenceId());
        assertEquals(106, messageSequence.getNextMessageId());
    }
}
