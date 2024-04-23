/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OutdoorVendorTest {

    @Test
    void getIntensityLevel() {
        OutdoorVendor vendor = new OutdoorVendor();
        vendor.setIntensityLevel("High");
        assertEquals("High", vendor.getIntensityLevel());
    }

    @Test
    void setIntensityLevel() {
        OutdoorVendor vendor = new OutdoorVendor();
        vendor.setIntensityLevel("Medium");
        assertEquals("Medium", vendor.getIntensityLevel());
    }

    @Test
    void testToString() {
        OutdoorVendor vendor = new OutdoorVendor();
        vendor.setVendorId(1);
        vendor.setIntensityLevel("Low");
        assertTrue(vendor.toString().contains("Low"));
    }

    @Test
    void testEquals() {
        OutdoorVendor vendor1 = new OutdoorVendor();
        vendor1.setVendorId(1);
        vendor1.setIntensityLevel("Medium");

        OutdoorVendor vendor2 = new OutdoorVendor();
        vendor2.setVendorId(1);
        vendor2.setIntensityLevel("Medium");

        assertEquals(vendor1, vendor2);
    }

    @Test
    void canEqual() {
        OutdoorVendor vendor1 = new OutdoorVendor();
        vendor1.setIntensityLevel("High");

        OutdoorVendor vendor2 = new OutdoorVendor();
        vendor2.setIntensityLevel("High");

        assertTrue(vendor1.canEqual(vendor2) && vendor2.canEqual(vendor1));
    }

    @Test
    void testHashCode() {
        OutdoorVendor vendor = new OutdoorVendor();
        vendor.setVendorId(1);
        vendor.setIntensityLevel("High");
        assertNotNull(vendor.hashCode());
    }
}
