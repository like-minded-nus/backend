/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.vendor;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.enums.VendorType;
import org.junit.jupiter.api.Test;

class VendorResponseDtoTest {

    @Test
    void testEquals() {
        VendorResponseDto vendor1 =
                VendorResponseDto.builder()
                        .vendorId(1)
                        .vendorName("Vendor 1")
                        .activityName("Activity 1")
                        .address("Address 1")
                        .phoneNumber(123456789)
                        .website("www.vendor1.com")
                        .passionId(1)
                        .vendorType(VendorType.OUTDOOR)
                        .build();

        VendorResponseDto vendor2 =
                VendorResponseDto.builder()
                        .vendorId(1)
                        .vendorName("Vendor 1")
                        .activityName("Activity 1")
                        .address("Address 1")
                        .phoneNumber(123456789)
                        .website("www.vendor1.com")
                        .passionId(1)
                        .vendorType(VendorType.OUTDOOR)
                        .build();

        VendorResponseDto vendor3 =
                VendorResponseDto.builder()
                        .vendorId(2)
                        .vendorName("Vendor 2")
                        .activityName("Activity 2")
                        .address("Address 2")
                        .phoneNumber(987654321)
                        .website("www.vendor2.com")
                        .passionId(2)
                        .vendorType(VendorType.INDOOR)
                        .build();

        // Test equals
        assertEquals(vendor1, vendor2);
        assertNotEquals(vendor1, vendor3);
    }

    @Test
    void testHashCode() {
        VendorResponseDto vendor1 =
                VendorResponseDto.builder()
                        .vendorId(1)
                        .vendorName("Vendor 1")
                        .activityName("Activity 1")
                        .address("Address 1")
                        .phoneNumber(123456789)
                        .website("www.vendor1.com")
                        .passionId(1)
                        .vendorType(VendorType.OUTDOOR)
                        .build();

        VendorResponseDto vendor2 =
                VendorResponseDto.builder()
                        .vendorId(1)
                        .vendorName("Vendor 1")
                        .activityName("Activity 1")
                        .address("Address 1")
                        .phoneNumber(123456789)
                        .website("www.vendor1.com")
                        .passionId(1)
                        .vendorType(VendorType.OUTDOOR)
                        .build();

        VendorResponseDto vendor3 =
                VendorResponseDto.builder()
                        .vendorId(2)
                        .vendorName("Vendor 2")
                        .activityName("Activity 2")
                        .address("Address 2")
                        .phoneNumber(987654321)
                        .website("www.vendor2.com")
                        .passionId(2)
                        .vendorType(VendorType.INDOOR)
                        .build();

        // Test hashCode
        assertEquals(vendor1.hashCode(), vendor2.hashCode());
        assertNotEquals(vendor1.hashCode(), vendor3.hashCode());
    }
}
