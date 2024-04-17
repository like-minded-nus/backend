/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.message;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import org.junit.jupiter.api.Test;

class MessageTest {

    @Test
    void getMessageId() {
        Message message = new Message();
        message.setMessageId(1);
        assertEquals(1, message.getMessageId());
    }

    @Test
    void getSenderProfileId() {
        Message message = new Message();
        message.setSenderProfileId(1001);
        assertEquals(1001, message.getSenderProfileId());
    }

    @Test
    void getReceiverProfileId() {
        Message message = new Message();
        message.setReceiverProfileId(1002);
        assertEquals(1002, message.getReceiverProfileId());
    }

    @Test
    void getText() {
        Message message = new Message();
        message.setText("Hello World!");
        assertEquals("Hello World!", message.getText());
    }

    @Test
    void getSentDateTime() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Message message = new Message();
        message.setSentDateTime(now);
        assertEquals(now, message.getSentDateTime());
    }

    @Test
    void getIsRead() {
        Message message = new Message();
        message.setIsRead("true");
        assertEquals("true", message.getIsRead());
    }

    @Test
    void setMessageId() {
        Message message = new Message();
        message.setMessageId(2);
        assertEquals(2, message.getMessageId());
    }

    @Test
    void setSenderProfileId() {
        Message message = new Message();
        message.setSenderProfileId(1003);
        assertEquals(1003, message.getSenderProfileId());
    }

    @Test
    void setReceiverProfileId() {
        Message message = new Message();
        message.setReceiverProfileId(1004);
        assertEquals(1004, message.getReceiverProfileId());
    }

    @Test
    void setText() {
        Message message = new Message();
        message.setText("Goodbye World!");
        assertEquals("Goodbye World!", message.getText());
    }

    @Test
    void setSentDateTime() {
        Timestamp later = new Timestamp(System.currentTimeMillis() + 10000);
        Message message = new Message();
        message.setSentDateTime(later);
        assertEquals(later, message.getSentDateTime());
    }

    @Test
    void setIsRead() {
        Message message = new Message();
        message.setIsRead("false");
        assertEquals("false", message.getIsRead());
    }

    @Test
    void testToString() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Message message = new Message(1, 1005, 1006, "Test Message", time, "false");
        String expected =
                "Message(messageId=1, senderProfileId=1005, receiverProfileId=1006, text=Test"
                        + " Message, sentDateTime="
                        + time.toString()
                        + ", isRead=false)";
        assertEquals(expected, message.toString());
    }

    @Test
    void testEquals() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Message message1 = new Message(1, 1007, 1008, "Content", time, "true");
        Message message2 = new Message(1, 1007, 1008, "Content", time, "true");
        assertEquals(message1, message2);
    }

    @Test
    void canEqual() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Message message1 = new Message(1, 1009, 1010, "Message", time, "true");
        Message message2 = new Message(1, 1009, 1010, "Message", time, "true");
        assertTrue(message1.canEqual(message2) && message2.canEqual(message1));
    }

    @Test
    void testHashCode() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Message message = new Message(1, 1011, 1012, "Hashcode Test", time, "yes");
        assertNotNull(message.hashCode());
    }

    @Test
    void builder() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Message message =
                Message.builder()
                        .messageId(3)
                        .senderProfileId(1013)
                        .receiverProfileId(1014)
                        .text("Built Message")
                        .sentDateTime(time)
                        .isRead("no")
                        .build();
        assertNotNull(message);
        assertEquals(3, message.getMessageId());
        assertEquals(1013, message.getSenderProfileId());
        assertEquals(1014, message.getReceiverProfileId());
        assertEquals("Built Message", message.getText());
        assertEquals(time, message.getSentDateTime());
        assertEquals("no", message.getIsRead());
    }
}
