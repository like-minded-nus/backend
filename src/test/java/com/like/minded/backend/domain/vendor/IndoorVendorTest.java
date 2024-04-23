/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IndoorVendorTest {

    @Test
    void getConversationFriendly() {
        IndoorVendor vendor = new IndoorVendor();
        vendor.setConversationFriendly("Yes");
        assertEquals("Yes", vendor.getConversationFriendly());
    }

    @Test
    void setConversationFriendly() {
        IndoorVendor vendor = new IndoorVendor();
        vendor.setConversationFriendly("No");
        assertEquals("No", vendor.getConversationFriendly());
    }

    @Test
    void testToString() {
        IndoorVendor vendor = new IndoorVendor();
        vendor.setVendorId(1);
        vendor.setConversationFriendly("Yes");
        String expectedString = "IndoorVendor(conversationFriendly=Yes)";
        assertTrue(vendor.toString().contains(expectedString));
    }

    @Test
    void testEquals() {
        IndoorVendor vendor1 = new IndoorVendor();
        vendor1.setVendorId(1);
        vendor1.setConversationFriendly("Yes");

        IndoorVendor vendor2 = new IndoorVendor();
        vendor2.setVendorId(1);
        vendor2.setConversationFriendly("Yes");

        assertEquals(vendor1, vendor2);
    }

    @Test
    void canEqual() {
        IndoorVendor vendor1 = new IndoorVendor();
        vendor1.setConversationFriendly("Yes");

        IndoorVendor vendor2 = new IndoorVendor();
        vendor2.setConversationFriendly("Yes");

        assertTrue(vendor1.canEqual(vendor2) && vendor2.canEqual(vendor1));
    }

    @Test
    void testHashCode() {
        IndoorVendor vendor = new IndoorVendor();
        vendor.setVendorId(1);
        vendor.setConversationFriendly("Yes");
        assertNotNull(vendor.hashCode());
    }
}
